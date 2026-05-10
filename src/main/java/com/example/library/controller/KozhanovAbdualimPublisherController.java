package com.example.library.controller;

import com.example.library.dto.KozhanovAbdualimPublisherDto;
import com.example.library.service.KozhanovAbdualimPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class KozhanovAbdualimPublisherController {

    private final KozhanovAbdualimPublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<KozhanovAbdualimPublisherDto>> getAll() {
        return ResponseEntity.ok(publisherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KozhanovAbdualimPublisherDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.findById(id));
    }

    @PostMapping
    public ResponseEntity<KozhanovAbdualimPublisherDto> create(@RequestBody KozhanovAbdualimPublisherDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KozhanovAbdualimPublisherDto> update(@PathVariable Long id,
                                                               @RequestBody KozhanovAbdualimPublisherDto dto) {
        return ResponseEntity.ok(publisherService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        publisherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}