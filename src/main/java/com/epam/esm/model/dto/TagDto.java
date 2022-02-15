package com.epam.esm.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TagDto {

    Long id;

    String name;

}
