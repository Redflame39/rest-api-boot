package com.epam.esm.repository.specification;

import com.epam.esm.model.entity.OrderType;
import com.epam.esm.repository.Specification;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CertificatesOrderSpecification implements Specification {

    OrderType orderType;

    @Override
    public String getQuery() {
        return orderType.getValue();
    }

    @Override
    public Object[] getQueryParams() {
        return new Object[0];
    }

}
