package com.example.library.repository;

import com.example.library.entity.KozhanovAbdualimLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KozhanovAbdualimLoanRepository extends JpaRepository<KozhanovAbdualimLoan, Long> {
    List<KozhanovAbdualimLoan> findByUserId(Long userId);
    List<KozhanovAbdualimLoan> findByReturnDateIsNull();
}