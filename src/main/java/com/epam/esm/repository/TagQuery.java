package com.epam.esm.repository;

public final class TagQuery {

    public static final String SQL_FIND_ALL = "select id, name from tag;";

    public static final String SQL_FIND_ID = "select id, name from tag where id = ?;";

    public static final String SQL_FIND_NAME = "select id, name from tag where name = ?;";

    public static final String SQL_FIND_CERTIFICATE_ID =
            "select id, name from tag left join certificate_tags on tag.id = certificate_tags.tag_id " +
                    "where certificate_tags.certificate_id = ?;";

    public static final String SQL_DELETE = "delete from tag where id = ?;";

    private TagQuery() { }
}
