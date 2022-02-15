package com.epam.esm.repository;

/**
 * The interface Specification is used by repositories to form complex queries.
 */
public interface Specification {

    /**
     * Forms the query for which the specification is responsible.
     *
     * @return the query as String.
     */
    String getQuery();

    /**
     * Get optional query params.
     *
     * @return array of parameter objects.
     */
    Object[] getQueryParams();

}
