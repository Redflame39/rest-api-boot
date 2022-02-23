package com.epam.esm.model.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Order {

    Long id;

    Long userId;

    Double price;

    Timestamp createDate;

    Timestamp lastUpdateDate;

    List<Certificate> orderedCertificates;

}
