package com.epam.esm.controller;

import com.epam.esm.model.dto.CreatingUserDto;
import com.epam.esm.model.entity.User;
import com.epam.esm.repository.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository<Long> userRepository; // FIXME temporary for development purposes

    @Autowired
    public UserController(UserRepository<Long> userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> read() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User read(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody CreatingUserDto user) { // FIXME return userDto
        return userRepository.create(user);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@PathVariable Long id, @RequestBody CreatingUserDto user) { // FIXME return userDto
        return userRepository.update(id, user);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@PathVariable Long id) { // FIXME return userDto
        return userRepository.delete(id);
    }
}
