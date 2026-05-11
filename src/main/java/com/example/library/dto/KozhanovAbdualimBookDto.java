package com.example.library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KozhanovAbdualimBookDto {
    private Long id;
    private String coverPath;

    @NotBlank(message = "Title is required")
    private String title;

    @Min(value = 0, message = "Publication year must be positive")
    private Integer publicationYear;

    @NotNull(message = "Total copies is required")
    @Min(value = 0, message = "Total copies must be non-negative")
    private Integer totalCopies;

    @NotNull(message = "Available copies is required")
    @Min(value = 0, message = "Available copies must be non-negative")
    private Integer availableCopies;

    @NotNull(message = "Publisher is required")
    private Long publisherId;

    @NotNull(message = "Category is required")
    private Long categoryId;

    private Set<Long> authorIds = new HashSet<>();
}