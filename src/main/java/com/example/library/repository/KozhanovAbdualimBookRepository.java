package com.example.library.repository;

import com.example.library.entity.KozhanovAbdualimBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KozhanovAbdualimBookRepository extends JpaRepository<KozhanovAbdualimBook, Long> {
    List<KozhanovAbdualimBook> findByTitleContainingIgnoreCase(String title);
}