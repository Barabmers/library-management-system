package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KozhanovAbdualimAuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
}