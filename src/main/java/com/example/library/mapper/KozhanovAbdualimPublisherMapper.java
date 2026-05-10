package com.example.library.mapper;

import com.example.library.dto.KozhanovAbdualimPublisherDto;
import com.example.library.entity.KozhanovAbdualimPublisher;
import org.springframework.stereotype.Component;

@Component
public class KozhanovAbdualimPublisherMapper {

    public KozhanovAbdualimPublisherDto toDto(KozhanovAbdualimPublisher publisher) {
        if (publisher == null) return null;

        KozhanovAbdualimPublisherDto dto = new KozhanovAbdualimPublisherDto();
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());
        dto.setAddress(publisher.getAddress());
        return dto;
    }

    public KozhanovAbdualimPublisher toEntity(KozhanovAbdualimPublisherDto dto) {
        if (dto == null) return null;

        KozhanovAbdualimPublisher publisher = new KozhanovAbdualimPublisher();
        publisher.setId(dto.getId());
        publisher.setName(dto.getName());
        publisher.setAddress(dto.getAddress());
        return publisher;
    }
}