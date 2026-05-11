package com.example.library.service;

import com.example.library.dto.KozhanovAbdualimLoanDto;
import com.example.library.entity.KozhanovAbdualimBook;
import com.example.library.entity.KozhanovAbdualimLoan;
import com.example.library.entity.KozhanovAbdualimUser;
import com.example.library.mapper.KozhanovAbdualimLoanMapper;
import com.example.library.repository.KozhanovAbdualimBookRepository;
import com.example.library.repository.KozhanovAbdualimLoanRepository;
import com.example.library.repository.KozhanovAbdualimUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class KozhanovAbdualimLoanService {

    private final KozhanovAbdualimLoanRepository loanRepository;
    private final KozhanovAbdualimUserRepository userRepository;
    private final KozhanovAbdualimBookRepository bookRepository;
    private final KozhanovAbdualimLoanMapper loanMapper;
    private final KozhanovAbdualimNotificationService notificationService;

    public List<KozhanovAbdualimLoanDto> findAll() {
        log.info("Fetching all loans");
        return loanRepository.findAll().stream()
                .map(loanMapper::toDto)
                .toList();
    }

    public KozhanovAbdualimLoanDto findById(Long id) {
        KozhanovAbdualimLoan loan = loanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Loan not found with id: " + id));
        return loanMapper.toDto(loan);
    }

    public List<KozhanovAbdualimLoanDto> findByUserId(Long userId) {
        log.info("Fetching loans for user {}", userId);
        return loanRepository.findByUserId(userId).stream()
                .map(loanMapper::toDto)
                .toList();
    }

    public List<KozhanovAbdualimLoanDto> findActive() {
        log.info("Fetching active loans (not returned)");
        return loanRepository.findByReturnDateIsNull().stream()
                .map(loanMapper::toDto)
                .toList();
    }

    public KozhanovAbdualimLoanDto borrow(Long userId, Long bookId) {
        log.info("User {} borrowing book {}", userId, bookId);
        KozhanovAbdualimUser user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        KozhanovAbdualimBook book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + bookId));
        if (book.getAvailableCopies() <= 0) {
            throw new IllegalArgumentException("No available copies of this book");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        KozhanovAbdualimLoan loan = new KozhanovAbdualimLoan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14));

        KozhanovAbdualimLoan saved = loanRepository.save(loan);
        notificationService.notifyBorrowConfirmation(
                user.getEmail(),
                user.getUsername(),
                book.getTitle(),
                loan.getDueDate().toString()
        );
        return loanMapper.toDto(saved);
    }

    public KozhanovAbdualimLoanDto returnBook(Long loanId) {
        log.info("Returning loan {}", loanId);
        KozhanovAbdualimLoan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NoSuchElementException("Loan not found with id: " + loanId));
        if (loan.getReturnDate() != null) {
            throw new IllegalArgumentException("This loan is already returned");
        }

        loan.setReturnDate(LocalDate.now());

        KozhanovAbdualimBook book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        KozhanovAbdualimLoan saved = loanRepository.save(loan);
        return loanMapper.toDto(saved);
    }

    public void delete(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new NoSuchElementException("Loan not found with id: " + id);
        }
        loanRepository.deleteById(id);
    }
}