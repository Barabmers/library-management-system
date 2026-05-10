package com.example.library.service;

import com.example.library.dto.KozhanovAbdualimPublisherDto;
import com.example.library.entity.KozhanovAbdualimPublisher;
import com.example.library.mapper.KozhanovAbdualimPublisherMapper;
import com.example.library.repository.KozhanovAbdualimPublisherRepository;
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
public class KozhanovAbdualimPublisherService {

    private final KozhanovAbdualimPublisherRepository publisherRepository;
    private final KozhanovAbdualimPublisherMapper publisherMapper;

    public List<KozhanovAbdualimPublisherDto> findAll() {
        log.info("Fetching all publishers");
        return publisherRepository.findAll().stream()
                .map(publisherMapper::toDto)
                .toList();
    }

    public KozhanovAbdualimPublisherDto findById(Long id) {
        KozhanovAbdualimPublisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Publisher not found with id: " + id));
        return publisherMapper.toDto(publisher);
    }

    public KozhanovAbdualimPublisherDto create(KozhanovAbdualimPublisherDto dto) {
        log.info("Creating publisher: {}", dto.getName());
        dto.setId(null);
        KozhanovAbdualimPublisher saved = publisherRepository.save(publisherMapper.toEntity(dto));
        return publisherMapper.toDto(saved);
    }

    public KozhanovAbdualimPublisherDto update(Long id, KozhanovAbdualimPublisherDto dto) {
        if (!publisherRepository.existsById(id)) {
            throw new NoSuchElementException("Publisher not found with id: " + id);
        }
        dto.setId(id);
        KozhanovAbdualimPublisher saved = publisherRepository.save(publisherMapper.toEntity(dto));
        return publisherMapper.toDto(saved);
    }

    public void delete(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new NoSuchElementException("Publisher not found with id: " + id);
        }
        publisherRepository.deleteById(id);
    }
}