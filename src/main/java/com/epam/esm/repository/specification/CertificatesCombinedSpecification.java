package com.epam.esm.repository.specification;

import com.epam.esm.repository.Specification;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class CertificatesCombinedSpecification implements Specification {

    List<Specification> specifications;

    @Override
    public String getQuery() {
        StringJoiner sj = new StringJoiner(" ");
        for (Specification s : specifications) {
            sj.add(s.getQuery());
        }
        return sj.toString();
    }

    @Override
    public Object[] getQueryParams() {
        List<Object> params = new ArrayList<>();
        for (Specification s : specifications) {
            Collections.addAll(params, s.getQueryParams());
        }
        return params.toArray();
    }
}
