package com.example.library.service;

import com.example.library.dto.KozhanovAbdualimCategoryDto;
import com.example.library.entity.KozhanovAbdualimCategory;
import com.example.library.mapper.KozhanovAbdualimCategoryMapper;
import com.example.library.repository.KozhanovAbdualimCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class KozhanovAbdualimCategoryService {

    private final KozhanovAbdualimCategoryRepository categoryRepository;
    private final KozhanovAbdualimCategoryMapper categoryMapper;

    public List<KozhanovAbdualimCategoryDto> findAll() {
        log.info("Fetching all categories");
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    public KozhanovAbdualimCategoryDto findById(Long id) {
        KozhanovAbdualimCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + id));
        return categoryMapper.toDto(category);
    }

    public KozhanovAbdualimCategoryDto create(KozhanovAbdualimCategoryDto dto) {
        log.info("Creating category: {}", dto.getName());
        dto.setId(null);
        KozhanovAbdualimCategory saved = categoryRepository.save(categoryMapper.toEntity(dto));
        return categoryMapper.toDto(saved);
    }

    public KozhanovAbdualimCategoryDto update(Long id, KozhanovAbdualimCategoryDto dto) {
        if (!categoryRepository.existsById(id)) {
            throw new NoSuchElementException("Category not found with id: " + id);
        }
        dto.setId(id);
        KozhanovAbdualimCategory saved = categoryRepository.save(categoryMapper.toEntity(dto));
        return categoryMapper.toDto(saved);
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NoSuchElementException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}