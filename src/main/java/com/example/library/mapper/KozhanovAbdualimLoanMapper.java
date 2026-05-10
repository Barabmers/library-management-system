package com.example.library.mapper;

import com.example.library.dto.KozhanovAbdualimLoanDto;
import com.example.library.entity.KozhanovAbdualimLoan;
import com.example.library.repository.KozhanovAbdualimBookRepository;
import com.example.library.repository.KozhanovAbdualimUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KozhanovAbdualimLoanMapper {
    private final KozhanovAbdualimUserRepository userRepository;
    private final KozhanovAbdualimBookRepository bookRepository;

    public KozhanovAbdualimLoanDto toDto(KozhanovAbdualimLoan loan) {
        if (loan == null) return null;

        KozhanovAbdualimLoanDto dto = new KozhanovAbdualimLoanDto();
        dto.setId(loan.getId());
        dto.setLoanDate(loan.getLoanDate());
        dto.setDueDate(loan.getDueDate());
        dto.setReturnDate(loan.getReturnDate());

        if (loan.getUser() != null) {
            dto.setUserId(loan.getUser().getId());
        }
        if (loan.getBook() != null) {
            dto.setBookId(loan.getBook().getId());
        }

        return dto;
    }

    public KozhanovAbdualimLoan toEntity(KozhanovAbdualimLoanDto dto) {
        if (dto == null) return null;

        KozhanovAbdualimLoan loan = new KozhanovAbdualimLoan();
        loan.setId(dto.getId());
        loan.setLoanDate(dto.getLoanDate());
        loan.setDueDate(dto.getDueDate());
        loan.setReturnDate(dto.getReturnDate());

        if (dto.getUserId() != null) {
            userRepository.findById(dto.getUserId()).ifPresent(loan::setUser);
        }
        if (dto.getBookId() != null) {
            bookRepository.findById(dto.getBookId()).ifPresent(loan::setBook);
        }

        return loan;
    }
}