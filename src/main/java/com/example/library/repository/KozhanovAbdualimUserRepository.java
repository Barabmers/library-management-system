package com.example.library.repository;

import com.example.library.entity.KozhanovAbdualimUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KozhanovAbdualimUserRepository extends JpaRepository<KozhanovAbdualimUser, Long> {
    Optional<KozhanovAbdualimUser> findByUsername(String username);
    Optional<KozhanovAbdualimUser> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}