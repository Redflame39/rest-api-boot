package com.epam.esm.repository.api;

import com.epam.esm.model.dto.CreatingUserDto;
import com.epam.esm.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository<K> {

    List<User> findAll();

    Optional<User> findById(K id);

    User create(CreatingUserDto dto);

    User update(K updateId, CreatingUserDto dto);

    User delete(K deleteId);

}