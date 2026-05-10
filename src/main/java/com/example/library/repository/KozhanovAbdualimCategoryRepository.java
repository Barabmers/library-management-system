package com.example.library.repository;

import com.example.library.entity.KozhanovAbdualimCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KozhanovAbdualimCategoryRepository extends JpaRepository<KozhanovAbdualimCategory, Long> {

}