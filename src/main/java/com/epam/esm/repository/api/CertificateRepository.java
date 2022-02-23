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
    Certificate create(UpdatingCertificateDto certificate);

    /**
     * Update certificate with given id. Updates only fields which is set to non-null in {@code UpdatingCertificateDto}.
     *
     * @param updateId    the id of certificate to update.
     * @param replacement the object that contains fields to update.
     * @return true if successfully updated, else false.
     */
    Certificate update(K updateId, UpdatingCertificateDto replacement);

    /**
     * Deletes certificate with given id.
     *
     * @param deleteId the id of certificate to delete.
     * @return true if successfully deleted, else false.
     */
    Certificate delete(K deleteId);

    /**
     * Finds certificates by {@code Specification} query.
     *
     * @param specification the specification which describes search criteria.
     * @return the list of found certificates.
     */
    List<Certificate> findBySpecification(Specification specification);
}
