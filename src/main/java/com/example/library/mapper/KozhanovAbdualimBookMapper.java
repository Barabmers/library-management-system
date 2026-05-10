package com.example.library.mapper;

import com.example.library.dto.KozhanovAbdualimBookDto;
import com.example.library.entity.KozhanovAbdualimAuthor;
import com.example.library.entity.KozhanovAbdualimBook;
import com.example.library.repository.KozhanovAbdualimAuthorRepository;
import com.example.library.repository.KozhanovAbdualimCategoryRepository;
import com.example.library.repository.KozhanovAbdualimPublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KozhanovAbdualimBookMapper {
    private final KozhanovAbdualimPublisherRepository publisherRepository;
    private final KozhanovAbdualimCategoryRepository categoryRepository;
    private final KozhanovAbdualimAuthorRepository authorRepository;

    public KozhanovAbdualimBookDto toDto(KozhanovAbdualimBook book) {
        if (book == null) return null;

        KozhanovAbdualimBookDto dto = new KozhanovAbdualimBookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setTotalCopies(book.getTotalCopies());
        dto.setAvailableCopies(book.getAvailableCopies());

        if (book.getPublisher() != null) {
            dto.setPublisherId(book.getPublisher().getId());
        }
        if (book.getCategory() != null) {
            dto.setCategoryId(book.getCategory().getId());
        }
        if (book.getAuthors() != null) {
            Set<Long> ids = book.getAuthors().stream()
                    .map(KozhanovAbdualimAuthor::getId)
                    .collect(Collectors.toSet());
            dto.setAuthorIds(ids);
        }

        return dto;
    }

    public KozhanovAbdualimBook toEntity(KozhanovAbdualimBookDto dto) {
        if (dto == null) return null;

        KozhanovAbdualimBook book = new KozhanovAbdualimBook();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setPublicationYear(dto.getPublicationYear());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());

        if (dto.getPublisherId() != null) {
            publisherRepository.findById(dto.getPublisherId())
                    .ifPresent(book::setPublisher);
        }
        if (dto.getCategoryId() != null) {
            categoryRepository.findById(dto.getCategoryId())
                    .ifPresent(book::setCategory);
        }
        if (dto.getAuthorIds() != null && !dto.getAuthorIds().isEmpty()) {
            Set<KozhanovAbdualimAuthor> authors = new HashSet<>(
                    authorRepository.findAllById(dto.getAuthorIds())
            );
            book.setAuthors(authors);
        }

        return book;
    }
}