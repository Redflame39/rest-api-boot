package com.epam.esm.repository.specification;

import com.epam.esm.model.entity.SortType;
import com.epam.esm.repository.Specification;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CertificatesSortSpecification implements Specification {

    SortType sortType;

    @Override
    public String getQuery() {
        return sortType.getSql();
    }

    @Override
    public Object[] getQueryParams() {
        return new Object[0];
    }
}
