package com.epam.esm.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum OrderType {

    ASC("ASC"),
    DESC("DESC");

    private final String value;

    @JsonCreator
    public static OrderType parseOrderType(String orderType) { // TODO create string to enum converter
        return Stream.of(values())
                .filter(title -> title.getValue().equalsIgnoreCase(orderType))
                .findFirst().orElse(ASC);
    }

}
