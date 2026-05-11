package com.example.library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class KozhanovAbdualimFileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveCover(Long bookId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }

        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                log.info("Created upload directory: {}", uploadPath);
            }

            String originalFilename = file.getOriginalFilename();
            String safeFilename = "cover_" + bookId + "_" + originalFilename;
            Path filePath = uploadPath.resolve(safeFilename);

            Files.copy(file.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            log.info("Saved cover for book {} at {}", bookId, filePath);

            return filePath.toString();
        } catch (IOException e) {
            log.error("Failed to save cover", e);
            throw new RuntimeException("Could not save file: " + e.getMessage());
        }
    }

    public byte[] loadCover(String coverPath) {
        try {
            Path path = Paths.get(coverPath);
            if (!Files.exists(path)) {
                throw new RuntimeException("Cover file not found: " + coverPath);
            }
            return Files.readAllBytes(path);
        } catch (IOException e) {
            log.error("Failed to load cover", e);
            throw new RuntimeException("Could not read file: " + e.getMessage());
        }
    }
}