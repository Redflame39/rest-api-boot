package com.epam.esm.repository.api;

import com.epam.esm.repository.CertificateColumnName;
import com.epam.esm.model.dto.UpdatingCertificateDto;
import com.epam.esm.model.entity.Certificate;
import com.epam.esm.repository.Specification;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * The interface Certificate repository describes operations with Certificate entity.
 *
 * @param <K> the identification type of Certificate entity.
 */
public interface CertificateRepository<K> {

    /**
     * The constant CERTIFICATE_MAPPER is used for mapping the ResultSet values to Certificate objects.
     */
    RowMapper<Certificate> CERTIFICATE_MAPPER = (ResultSet rs, int rowNum) ->
            Certificate.builder()
                    .id(rs.getLong(CertificateColumnName.ID))
                    .name(rs.getString(CertificateColumnName.NAME))
                    .description(rs.getString(CertificateColumnName.DESCRIPTION))
                    .price(rs.getDouble(CertificateColumnName.PRICE))
                    .duration(rs.getInt(CertificateColumnName.DURATION))
                    .createDate(rs.getTimestamp(CertificateColumnName.CREATE_DATE))
                    .lastUpdateDate(rs.getTimestamp(CertificateColumnName.LAST_UPDATE_DATE))
                    .build();

    /**
     * Finds certificate by its id.
     *
     * @param id the id of certificate to find.
     * @return Optional certificate object.
     */
    Optional<Certificate> findById(K id);

    /**
     * Create certificate with given values in {@code CreatingCertificateDto}.
     *
     * @param certificate the certificate to be created.
     * @return the id of created certificate.
     */
    K create(UpdatingCertificateDto certificate);

    /**
     * Update certificate with given id. Updates only fields which is set to non-null in {@code UpdatingCertificateDto}.
     *
     * @param updateId    the id of certificate to update.
     * @param replacement the object that contains fields to update.
     * @return true if successfully updated, else false.
     */
    boolean update(K updateId, UpdatingCertificateDto replacement);

    /**
     * Deletes certificate with given id.
     *
     * @param deleteId the id of certificate to delete.
     * @return true if successfully deleted, else false.
     */
    boolean delete(K deleteId);

    /**
     * Finds certificates by {@code Specification} query.
     *
     * @param specification the specification which describes search criteria.
     * @return the list of found certificates.
     */
    List<Certificate> findBySpecification(Specification specification);
}
