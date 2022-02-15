package com.epam.esm.repository.specification;

import com.epam.esm.repository.CertificateColumnName;
import com.epam.esm.repository.Specification;
import com.epam.esm.repository.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CertificatesByDescriptionPartSpecification implements Specification {

    String descriptionPart;

    @Override
    public String getQuery() {
        return String.format("and %s.%s like ?", TableName.TABLE_CERTIFICATES, CertificateColumnName.DESCRIPTION);
    }

    @Override
    public Object[] getQueryParams() {
        return new Object[]{String.format("%%%s%%", descriptionPart)};
    }
}
