package com.example.library.repository;

import com.example.library.entity.KozhanovAbdualimBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KozhanovAbdualimBookRepository extends JpaRepository<KozhanovAbdualimBook, Long> {
    @Query("""

            SELECT b FROM KozhanovAbdualimBook b
        WHERE (:title = '' OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
          AND (:categoryId IS NULL OR b.category.id = :categoryId)
          AND (:publisherId IS NULL OR b.publisher.id = :publisherId)
        """)
    Page<KozhanovAbdualimBook> search(@Param("title") String title,
                                      @Param("categoryId") Long categoryId,
                                      @Param("publisherId") Long publisherId,
                                      Pageable pageable);
    }
