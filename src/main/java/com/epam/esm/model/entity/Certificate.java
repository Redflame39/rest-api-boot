package com.epam.esm.model.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@ToString
public class Certificate {

    Long id;

    Double price;

    Integer duration;

    String name;

    String description;

    Timestamp createDate;

    Timestamp lastUpdateDate;

    List<Tag> tags;

}
