package com.epam.esm.repository;

public final class CertificateTagQuery {

    public static final String SQL_CLEAR_TAGS = "delete from certificate_tags where certificate_id = ?;";

    public static final String SQL_ADD_TAG = "insert into certificate_tags (certificate_id, tag_id) values (?, ?);";

    private CertificateTagQuery() { }
}
