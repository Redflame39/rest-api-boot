package com.epam.esm.converter;

import com.epam.esm.model.dto.CertificateDto;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.entity.Certificate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CertificateToCertificateDtoConverter implements Converter<Certificate, CertificateDto> { // TODO add converter to formatters

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS");

    @Override
    public CertificateDto convert(Certificate source) {
        TagToTagDtoConverter tagDtoConverter = new TagToTagDtoConverter();
        List<TagDto> tagDtos = source.getTags() != null
                ? source.getTags()
                .stream()
                .map(tagDtoConverter::convert)
                .collect(Collectors.toList())
                : Collections.emptyList();
        LocalDateTime createLocalDateTime = source.getCreateDate().toLocalDateTime();
        LocalDateTime updateLocalDateTime = source.getLastUpdateDate().toLocalDateTime();
        String createDate = createLocalDateTime.format(FORMATTER);
        String updateDate = updateLocalDateTime.format(FORMATTER);
        return CertificateDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .price(source.getPrice())
                .duration(source.getDuration())
                .createDate(createDate)
                .lastUpdateDate(updateDate)
                .tags(tagDtos)
                .build();
    }

}
