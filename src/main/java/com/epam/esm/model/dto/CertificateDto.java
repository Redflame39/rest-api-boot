package com.epam.esm.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@ToString
public class CertificateDto {

    Long id;

    Double price;

    Integer duration;

    String name;

    String description;

    String createDate;

    String lastUpdateDate;

    List<TagDto> tags;
}
