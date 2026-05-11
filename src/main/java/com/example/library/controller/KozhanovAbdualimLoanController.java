package com.example.library.controller;

import com.example.library.dto.KozhanovAbdualimLoanDto;
import com.example.library.service.KozhanovAbdualimLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class KozhanovAbdualimLoanController {

    private final KozhanovAbdualimLoanService loanService;

    @GetMapping
    public ResponseEntity<List<KozhanovAbdualimLoanDto>> getAll() {
        return ResponseEntity.ok(loanService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KozhanovAbdualimLoanDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<KozhanovAbdualimLoanDto>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(loanService.findByUserId(userId));
    }

    @GetMapping("/active")
    public ResponseEntity<List<KozhanovAbdualimLoanDto>> getActive() {
        return ResponseEntity.ok(loanService.findActive());
    }

    @PostMapping("/borrow")
    public ResponseEntity<KozhanovAbdualimLoanDto> borrow(@RequestParam Long userId, @RequestParam Long bookId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.borrow(userId, bookId));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<KozhanovAbdualimLoanDto> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.returnBook(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}