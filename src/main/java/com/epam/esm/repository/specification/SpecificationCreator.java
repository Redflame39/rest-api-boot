package com.epam.esm.repository.specification;

import com.epam.esm.model.entity.OrderType;
import com.epam.esm.model.entity.SortType;
import com.epam.esm.repository.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The class SpecificationCreator is responsible for creating complex Specification to find Certificates
 * by multiple optional params.
 */
@Component
public class SpecificationCreator {

    /**
     * Creates specification by given params.
     *
     * @param name        the part of the certificate's name.
     * @param description the part of the certificate's description.
     * @param tag         the tag name which certificate should contain.
     * @param sort        the sort type of result certificates list.
     * @param order       the order type of result certificates list.
     * @return complex specification for querying certificates by multiple optional parameters.
     */
    public Specification createSpecification(String name, String description, String tag,
                                             SortType sort, OrderType order) {
        List<Specification> specifications = new ArrayList<>();
        if (name != null) {
            Specification specification = new CertificatesByNamePartSpecification(name);
            specifications.add(specification);
        }
        if (description != null) {
            Specification specification = new CertificatesByDescriptionPartSpecification(description);
            specifications.add(specification);
        }
        if (tag != null) {
            Specification specification = new CertificatesByTagNameSpecification(tag);
            specifications.add(specification);
        }
        Specification sortSpecification = new CertificatesSortSpecification(sort);
        specifications.add(sortSpecification);
        if (sort != SortType.NONE) {
            Specification orderSpecification = new CertificatesOrderSpecification(order);
            specifications.add(orderSpecification);
        }
        return new CertificatesCombinedSpecification(specifications);
    }
}
