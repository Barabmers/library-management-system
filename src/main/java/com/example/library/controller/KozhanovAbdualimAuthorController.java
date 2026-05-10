package com.example.library.controller;

import com.example.library.dto.KozhanovAbdualimAuthorDto;
import com.example.library.service.KozhanovAbdualimAuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class KozhanovAbdualimAuthorController {

    private final KozhanovAbdualimAuthorService authorService;

    @GetMapping
    public ResponseEntity<List<KozhanovAbdualimAuthorDto>> getAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KozhanovAbdualimAuthorDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<KozhanovAbdualimAuthorDto> create(@Valid @RequestBody KozhanovAbdualimAuthorDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KozhanovAbdualimAuthorDto> update(@Valid @PathVariable Long id,
                                                            @RequestBody KozhanovAbdualimAuthorDto dto) {
        return ResponseEntity.ok(authorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}