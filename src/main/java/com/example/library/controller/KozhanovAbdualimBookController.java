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
import com.example.library.service.KozhanovAbdualimFileService;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class KozhanovAbdualimBookController {

    private final KozhanovAbdualimBookService bookService;
    private final KozhanovAbdualimFileService fileService;

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

    @PostMapping(value = "/{id}/cover", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<KozhanovAbdualimBookDto> uploadCover(@PathVariable Long id,
                                                               @RequestParam("file") MultipartFile file) {
        KozhanovAbdualimBookDto book = bookService.findById(id);
        String path = fileService.saveCover(id, file);
        book.setCoverPath(path);
        return ResponseEntity.ok(bookService.update(id, book));
    }

    @GetMapping("/{id}/cover")
    public ResponseEntity<byte[]> downloadCover(@PathVariable Long id) {
        KozhanovAbdualimBookDto book = bookService.findById(id);
        if (book.getCoverPath() == null) {
            return ResponseEntity.notFound().build();
        }
        byte[] data = fileService.loadCover(book.getCoverPath());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(data);
    }
}