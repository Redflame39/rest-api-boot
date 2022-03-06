package com.epam.esm.service.api;

import com.epam.esm.model.dto.UpdatingUserDto;
import com.epam.esm.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll(Integer pageNum, Integer pageSize);

    UserDto findById(Long id);

    UserDto create(UpdatingUserDto userDto);

    UserDto update(Long updateId, UpdatingUserDto userDto);

    UserDto delete(Long deleteId);

}
