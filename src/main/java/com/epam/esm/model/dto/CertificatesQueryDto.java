package com.epam.esm.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CertificatesQueryDto {

    List<TagDto> tags;

    String name;

    String description;

}