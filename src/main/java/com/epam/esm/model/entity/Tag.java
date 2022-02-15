package com.epam.esm.model.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode
@ToString
public class Tag {

    Long id;

    String name;

}
