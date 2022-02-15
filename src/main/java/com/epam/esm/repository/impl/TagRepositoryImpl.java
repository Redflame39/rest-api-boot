package com.epam.esm.repository.impl;

import com.epam.esm.repository.TagQuery;
import com.epam.esm.repository.api.TagRepository;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TagRepositoryImpl implements TagRepository<Long> {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public TagRepositoryImpl(JdbcTemplate jdbcTemplate, ApplicationContext applicationContext) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(applicationContext.getBean(DataSource.class))
                .withTableName("tag")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(TagQuery.SQL_FIND_ALL, TAG_MAPPER);
    }

    @Override
    public Optional<Tag> findById(Long id) {
        try {
            Tag result = jdbcTemplate.queryForObject(TagQuery.SQL_FIND_ID, TAG_MAPPER, id);
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tag> findByName(String name) {
        try {
            Tag result = jdbcTemplate.queryForObject(TagQuery.SQL_FIND_NAME, TAG_MAPPER, name);
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Tag> findByCertificateId(Long id) {
        return jdbcTemplate.query(TagQuery.SQL_FIND_CERTIFICATE_ID, TAG_MAPPER, id);
    }

    @Override
    public Long create(TagDto tag) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", tag.getName());
        Integer id = (Integer) simpleJdbcInsert.executeAndReturnKey(parameters);
        return (long) id;
    }

    @Override
    public boolean delete(Long deleteId) {
        int affectedRows = jdbcTemplate.update(TagQuery.SQL_DELETE, deleteId);

        return affectedRows > 0;
    }

}