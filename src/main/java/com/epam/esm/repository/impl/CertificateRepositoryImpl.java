package com.epam.esm.repository.impl;

import com.epam.esm.repository.CertificateColumnName;
import com.epam.esm.repository.CertificateQuery;
import com.epam.esm.repository.Specification;
import com.epam.esm.repository.TableName;
import com.epam.esm.repository.api.CertificateRepository;
import com.epam.esm.model.dto.UpdatingCertificateDto;
import com.epam.esm.model.entity.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.*;

@Repository
public class CertificateRepositoryImpl implements CertificateRepository<Long> {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public CertificateRepositoryImpl(JdbcTemplate jdbcTemplate, ApplicationContext applicationContext) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(applicationContext.getBean(DataSource.class))
                .withTableName(TableName.TABLE_CERTIFICATES)
                .usingGeneratedKeyColumns(CertificateColumnName.ID);
    }

    @Override
    public List<Certificate> findBySpecification(Specification specification) {
        return jdbcTemplate.query(CertificateQuery.SQL_FIND_ALL + specification.getQuery(), CERTIFICATE_MAPPER, specification.getQueryParams());
    }

    @Override
    public Optional<Certificate> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(CertificateQuery.SQL_FIND_ID, CERTIFICATE_MAPPER, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Long create(UpdatingCertificateDto certificate) {
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(CertificateColumnName.NAME, certificate.getName());
        parameters.put(CertificateColumnName.DESCRIPTION, certificate.getDescription());
        parameters.put(CertificateColumnName.PRICE, certificate.getPrice());
        parameters.put(CertificateColumnName.DURATION, certificate.getDuration());
        parameters.put(CertificateColumnName.CREATE_DATE, currentTime);
        parameters.put(CertificateColumnName.LAST_UPDATE_DATE, currentTime);
        Integer id = (Integer) simpleJdbcInsert.executeAndReturnKey(parameters);
        return (long) id;
    }

    @Override
    public boolean update(Long updateId, UpdatingCertificateDto replacement) {
        int affectedRows = jdbcTemplate.update(CertificateQuery.SQL_UPDATE,
                replacement.getName(),
                replacement.getDescription(),
                replacement.getPrice(),
                replacement.getDuration(),
                updateId);

        return affectedRows > 0;
    }

    @Override
    public boolean delete(Long deleteId) {
        int affectedRows = jdbcTemplate.update(CertificateQuery.SQL_DELETE, deleteId);

        return affectedRows > 0;
    }
}
