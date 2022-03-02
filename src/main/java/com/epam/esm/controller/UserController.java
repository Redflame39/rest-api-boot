package com.epam.esm.controller;

import com.epam.esm.model.dto.OrderDto;
import com.epam.esm.model.dto.UpdatingUserDto;
import com.epam.esm.model.dto.UserDto;
import com.epam.esm.service.api.OrderService;
import com.epam.esm.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> read() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto read(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UpdatingUserDto user) {
        return userService.create(user);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto update(@PathVariable Long id, @RequestBody UpdatingUserDto user) {
        return userService.update(id, user);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto update(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PostMapping(value = "/{id}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@PathVariable Long id, @RequestBody List<Long> certificatesIds) {
        return orderService.create(id, certificatesIds);
    }

    @GetMapping(value = "/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> readOrders(@PathVariable Long id) {
        return orderService.findByUserId(id);
    }

}
