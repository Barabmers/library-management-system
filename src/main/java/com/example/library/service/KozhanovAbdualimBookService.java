package com.example.library.service;

import com.example.library.dto.KozhanovAbdualimBookDto;
import com.example.library.entity.KozhanovAbdualimBook;
import com.example.library.mapper.KozhanovAbdualimBookMapper;
import com.example.library.repository.KozhanovAbdualimBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class KozhanovAbdualimBookService {

    private final KozhanovAbdualimBookRepository bookRepository;
    private final KozhanovAbdualimBookMapper bookMapper;

    public List<KozhanovAbdualimBookDto> findAll() {
        log.info("Fetching all books");
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public KozhanovAbdualimBookDto findById(Long id) {
        log.info("Fetching book with id {}", id);
        KozhanovAbdualimBook book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));
        return bookMapper.toDto(book);
    }

    public Page<KozhanovAbdualimBookDto> search(String title, Long categoryId, Long publisherId, Pageable pageable) {
        log.info("Searching books: title={}, categoryId={}, publisherId={}, pageable={}",
                title, categoryId, publisherId, pageable);
        String titleParam = (title == null) ? "" : title;
        return bookRepository.search(titleParam, categoryId, publisherId, pageable)
                .map(bookMapper::toDto);
    }

    public KozhanovAbdualimBookDto create(KozhanovAbdualimBookDto dto) {
        log.info("Creating new book: {}", dto.getTitle());
        dto.setId(null);
        KozhanovAbdualimBook book = bookMapper.toEntity(dto);
        KozhanovAbdualimBook saved = bookRepository.save(book);
        return bookMapper.toDto(saved);
    }

    public KozhanovAbdualimBookDto update(Long id, KozhanovAbdualimBookDto dto) {
        log.info("Updating book with id {}", id);
        if (!bookRepository.existsById(id)) {
            throw new NoSuchElementException("Book not found with id: " + id);
        }
        dto.setId(id);
        KozhanovAbdualimBook book = bookMapper.toEntity(dto);
        KozhanovAbdualimBook saved = bookRepository.save(book);
        return bookMapper.toDto(saved);
    }

    public void delete(Long id) {
        log.info("Deleting book with id {}", id);
        if (!bookRepository.existsById(id)) {
            throw new NoSuchElementException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}