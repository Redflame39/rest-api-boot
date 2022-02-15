package com.epam.esm.model.entity;

import com.epam.esm.repository.CertificateColumnName;
import com.epam.esm.repository.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum SortType {

    NAME(String.format("order by %s.%s", TableName.TABLE_CERTIFICATES, CertificateColumnName.NAME)),
    CREATE_DATE(String.format("order by %s.%s", TableName.TABLE_CERTIFICATES, CertificateColumnName.CREATE_DATE)),
    LAST_UPDATE_DATE(String.format("order by %s.%s", TableName.TABLE_CERTIFICATES, CertificateColumnName.LAST_UPDATE_DATE)),
    NONE("");

    private final String sql;

    public static SortType parseSortType(String sortType) {
        return Stream.of(values())
                .filter(title -> title.getSql().equalsIgnoreCase(sortType))
                .findFirst().orElse(NONE);
    }

}
