package com.example.library.controller;

import com.example.library.dto.KozhanovAbdualimCategoryDto;
import com.example.library.service.KozhanovAbdualimCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class KozhanovAbdualimCategoryController {

    private final KozhanovAbdualimCategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<KozhanovAbdualimCategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KozhanovAbdualimCategoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<KozhanovAbdualimCategoryDto> create(@Valid @RequestBody KozhanovAbdualimCategoryDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KozhanovAbdualimCategoryDto> update(@Valid @PathVariable Long id,
                                                              @RequestBody KozhanovAbdualimCategoryDto dto) {
        return ResponseEntity.ok(categoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}