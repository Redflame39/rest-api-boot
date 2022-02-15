package com.epam.esm.repository.api;

import com.epam.esm.repository.TagColumnName;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * The interface Tag repository which describes operations with Tag entity.
 *
 * @param <K> the identifier type of Tag entity.
 */
public interface TagRepository<K> {

    /**
     * The constant TAG_MAPPER is used for mapping the ResultSet values to Tag objects.
     */
    RowMapper<Tag> TAG_MAPPER = (ResultSet rs, int rowNum) ->
            Tag.builder()
                    .id(rs.getLong(TagColumnName.ID))
                    .name(rs.getString(TagColumnName.NAME))
                    .build();

    /**
     * Finds all tags.
     *
     * @return the list of all tags.
     */
    List<Tag> findAll();

    /**
     * Finds tag by its id.
     *
     * @param id the id of tag to find.
     * @return Optional tag object.
     */
    Optional<Tag> findById(K id);

    /**
     * Create certificate with given values in {@code TagDto}.
     *
     * @param t the tag to be created
     * @return the id of created tag.
     */
    K create(TagDto t);

    /**
     * Delete tag with given id.
     *
     * @param deleteId the id of tag to delete.
     * @return true if successfully deleted, else false.
     */
    boolean delete(K deleteId);

    /**
     * Finds certificate by its name.
     *
     * @param name the name of certificate to find.
     * @return Optional value of Tag object.
     */
    Optional<Tag> findByName(String name);

    /**
     * Finds all tags of certificate by certificate id.
     *
     * @param id the id of certificate.
     * @return the list of certificate tags.
     */
    List<Tag> findByCertificateId(K id);

}
