package com.epam.esm.repository.specification;

import com.epam.esm.repository.Specification;
import com.epam.esm.repository.TableName;
import com.epam.esm.repository.TagColumnName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CertificatesByTagNameSpecification implements Specification {

    String tagName;

    @Override
    public String getQuery() {
        return String.format("and %s.%s = ?", TableName.TABLE_TAGS, TagColumnName.NAME);
    }

    @Override
    public Object[] getQueryParams() {
        return new Object[]{tagName};
    }
}
