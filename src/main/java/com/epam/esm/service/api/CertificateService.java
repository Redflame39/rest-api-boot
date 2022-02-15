package com.epam.esm.service.api;

import com.epam.esm.model.dto.CertificateDto;
import com.epam.esm.model.dto.UpdatingCertificateDto;
import com.epam.esm.model.entity.OrderType;
import com.epam.esm.model.entity.SortType;

import java.util.List;

/**
 * The interface Certificate service. Describes CRUD operations for Certificate entity.
 */
public interface CertificateService {

    /**
     * Create certificate with given parameters from {@code CreatingCertificateDto}.
     *
     * @param certificate {@code CreatingCertificateDto} to add.
     * @return the certificate dto with its dates and id.
     * @throws com.epam.esm.exception.EntityNotCreatedException when certificate cannot be created.
     */
    CertificateDto create(UpdatingCertificateDto certificate);

    /**
     * Finds all certificates by optional query params and returns list of CertificateDto.
     *
     * @param tagName     the tag name of certificate to find. May be null.
     * @param name        the name of certificate to find. May be part of the name or null.
     * @param description the description of certificate to find. May be part of the description to find or null.
     * @param sort        the {@code SortType} which describes the order of objects in result list.
     * @param order       the {@code OrderType} which may be either ASC or DESC.
     * @return the list of {@code CertificateDto}'s that satisfies the query.
     */
    List<CertificateDto> findAll(String tagName, String name, String description, SortType sort, OrderType order);

    /**
     * Finds certificate by given id.
     *
     * @param id the id of certificate to find.
     * @return the {@code CertificateDto} if query executes successfully.
     * @throws com.epam.esm.exception.EntityNotFoundException when certificate with given id cannot be found.
     */
    CertificateDto findById(Long id);

    /**
     * Updates certificate with given id. Fields to update are described in {@code UpdatingCertificateDto}.
     * If field should not be updated, it sets to null in {@code UpdatingCertificateDto}.
     *
     * @param updateId    the id of certificate to update.
     * @param replacement dto which contains replacement values.
     * @return updated certificate.
     * @throws com.epam.esm.exception.EntityNotUpdatedException when entity cannot be updated.
     */
    CertificateDto update(Long updateId, UpdatingCertificateDto replacement);

    /**
     * Deletes certificate by its id.
     *
     * @param deleteId the id of certificate to delete.
     * @return deleted certificate.
     * @throws com.epam.esm.exception.EntityNotUpdatedException when entity cannot be deleted.
     */
    CertificateDto delete(Long deleteId);

}
