package com.epam.esm.repository.api;

import com.epam.esm.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository<K> {

    List<User> findAll(Integer pageNum, Integer pageSize);

    Optional<User> findById(K id);

    User create(User user);

    User update(K updateId, User dto);

    User delete(K deleteId);

}
