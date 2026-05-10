package com.example.library.mapper;

import com.example.library.dto.KozhanovAbdualimCategoryDto;
import com.example.library.entity.KozhanovAbdualimCategory;
import org.springframework.stereotype.Component;

@Component
public class KozhanovAbdualimCategoryMapper {
    public KozhanovAbdualimCategoryDto toDto(KozhanovAbdualimCategory category) {
        if (category == null) return null;

        KozhanovAbdualimCategoryDto dto = new KozhanovAbdualimCategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public KozhanovAbdualimCategory toEntity(KozhanovAbdualimCategoryDto dto) {
        if (dto == null) return null;

        KozhanovAbdualimCategory category = new KozhanovAbdualimCategory();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }
}