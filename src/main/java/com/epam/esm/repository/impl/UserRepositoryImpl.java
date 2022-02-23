package com.epam.esm.repository.impl;

import com.epam.esm.model.dto.CreatingUserDto;
import com.epam.esm.model.entity.User;
import com.epam.esm.repository.api.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository<Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public User create(CreatingUserDto dto) {
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setCreateDate(currentTime);
        user.setLastUpdateDate(currentTime);
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public User update(Long updateId, CreatingUserDto dto) { // FIXME check null fields in dto
        User user = entityManager.find(User.class, updateId);
        entityManager.detach(user);
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
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

}
