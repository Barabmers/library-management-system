package com.example.library.controller;

import com.example.library.dto.KozhanovAbdualimBookDto;
import com.example.library.service.KozhanovAbdualimBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class KozhanovAbdualimBookController {

    private final KozhanovAbdualimBookService bookService;

    @GetMapping
    public ResponseEntity<Page<KozhanovAbdualimBookDto>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long publisherId,
            Pageable pageable) {
        return ResponseEntity.ok(bookService.search(title, categoryId, publisherId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KozhanovAbdualimBookDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<KozhanovAbdualimBookDto> create(@Valid @RequestBody KozhanovAbdualimBookDto dto) {
        KozhanovAbdualimBookDto created = bookService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KozhanovAbdualimBookDto> update(@Valid @PathVariable Long id, @RequestBody KozhanovAbdualimBookDto dto) {
        return ResponseEntity.ok(bookService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}