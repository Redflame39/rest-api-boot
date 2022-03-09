package com.epam.esm.repository.impl;

import com.epam.esm.model.entity.User;
import com.epam.esm.repository.api.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository<Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll(Integer pageNum, Integer pageSize) {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public User create(User user) {
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());
        user.setCreateDate(currentTime);
        user.setLastUpdateDate(currentTime);
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public User update(Long updateId, User dto) {
        User user = entityManager.find(User.class, updateId);
        entityManager.detach(user);
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setLastUpdateDate(currentTime);
        entityManager.merge(user);
        return user;
    }

    @Override
    @Transactional
    public User delete(Long deleteId) {
        User user = entityManager.find(User.class, deleteId);
        entityManager.remove(user);
        return user;
    }

    @Override
    public Long countUsers() {
        return (Long) entityManager.createQuery("select count(id) from User").getSingleResult();
    }
}
