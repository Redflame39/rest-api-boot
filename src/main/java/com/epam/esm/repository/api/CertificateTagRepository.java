package com.epam.esm.repository.api;

import com.epam.esm.model.dto.TagDto;

import java.util.List;

/**
 * The interface Certificate tag repository which is used for working with certificate-tag id pairs.
 *
 * @param <K> the identifier type of certificate entity.
 */
public interface CertificateTagRepository<K> {

    /**
     * Creates certificate-tag id pairs for given certificate and tags list.
     *
     * @param certificateId the certificate id to pair.
     * @param tags          the tags to pair.
     * @return true if successfully added, else false.
     */
    boolean addTags(K certificateId, List<TagDto> tags);

    /**
     * Clear tags for given certificate id.
     *
     * @param certificateId the certificate id.
     * @return true if successfully cleared, else false.
     */
    boolean clearTags(K certificateId);

}
