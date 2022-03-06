package com.epam.esm.service.api;

import com.epam.esm.model.dto.TagDto;

import java.util.List;

/**
 * The interface Tag service. Describes CRD operations for Tag entity.
 */
public interface TagService {

    /**
     * Creates tag with given name value in {@code TagDto}.
     *
     * @param tag the tag to create.
     * @return created tag.
     * @throws com.epam.esm.exception.EntityNotCreatedException when tag cannot be created.
     */
    TagDto create(TagDto tag);

    /**
     * Finds all tags.
     *
     * @return the list of all tags.
     */
    List<TagDto> findAll(Integer pageNum, Integer pageSize);

    /**
     * Find all tags of certificate by its id.
     *
     * @param id the id of certificate which tags should be found.
     * @return the list of found tags.
     */
    List<TagDto> findByCertificateId(Long id);

    /**
     * Finds tag by its id.
     *
     * @param id the id of tag to find.
     * @return found tag.
     * @throws com.epam.esm.exception.EntityNotFoundException when tag cannot be found.
     */
    TagDto findById(Long id);

    /**
     * Delete tag by its id.
     *
     * @param deleteId the id of tag to delete.
     * @return deleted tag.
     * @throws com.epam.esm.exception.EntityNotUpdatedException when tag cannot be deleted.
     */
    TagDto delete(Long deleteId);

    List<TagDto> findMostUsedUserTag(Long userId);

}
