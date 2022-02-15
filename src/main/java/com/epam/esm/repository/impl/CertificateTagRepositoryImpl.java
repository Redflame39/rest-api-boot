package com.epam.esm.repository.impl;

import com.epam.esm.repository.CertificateTagQuery;
import com.epam.esm.repository.api.CertificateTagRepository;
import com.epam.esm.model.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CertificateTagRepositoryImpl implements CertificateTagRepository<Long> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CertificateTagRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean clearTags(Long certificateId) {
        int affectedRows = jdbcTemplate.update(CertificateTagQuery.SQL_CLEAR_TAGS, certificateId);

        return affectedRows > 0;
    }

    @Override
    public boolean addTags(Long certificateId, List<TagDto> tags) {
        for (TagDto tag : tags) {
            int affectedRows = jdbcTemplate.update(CertificateTagQuery.SQL_ADD_TAG, certificateId, tag.getId());
            if (affectedRows <= 0) {
                return false;
            }
        }
        return true;
    }

}
