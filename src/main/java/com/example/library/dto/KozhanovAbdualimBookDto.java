package com.example.library.dto;

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
    private String title;
    private Integer publicationYear;
    private Integer totalCopies;
    private Integer availableCopies;
    private Long publisherId;
    private Long categoryId;
    private Set<Long> authorIds = new HashSet<>();
}