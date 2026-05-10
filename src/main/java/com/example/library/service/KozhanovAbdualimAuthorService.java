package com.example.library.service;

import com.example.library.dto.KozhanovAbdualimAuthorDto;
import com.example.library.entity.KozhanovAbdualimAuthor;
import com.example.library.mapper.KozhanovAbdualimAuthorMapper;
import com.example.library.repository.KozhanovAbdualimAuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class KozhanovAbdualimAuthorService {

    private final KozhanovAbdualimAuthorRepository authorRepository;
    private final KozhanovAbdualimAuthorMapper authorMapper;

    public List<KozhanovAbdualimAuthorDto> findAll() {
        log.info("Fetching all authors");
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .toList();
    }

    public KozhanovAbdualimAuthorDto findById(Long id) {
        KozhanovAbdualimAuthor author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + id));
        return authorMapper.toDto(author);
    }

    public KozhanovAbdualimAuthorDto create(KozhanovAbdualimAuthorDto dto) {
        log.info("Creating author: {} {}", dto.getFirstName(), dto.getLastName());
        dto.setId(null);
        KozhanovAbdualimAuthor saved = authorRepository.save(authorMapper.toEntity(dto));
        return authorMapper.toDto(saved);
    }

    public KozhanovAbdualimAuthorDto update(Long id, KozhanovAbdualimAuthorDto dto) {
        if (!authorRepository.existsById(id)) {
            throw new NoSuchElementException("Author not found with id: " + id);
        }
        dto.setId(id);
        KozhanovAbdualimAuthor saved = authorRepository.save(authorMapper.toEntity(dto));
        return authorMapper.toDto(saved);
    }

    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new NoSuchElementException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}