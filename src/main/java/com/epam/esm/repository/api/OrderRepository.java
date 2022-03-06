package com.epam.esm.repository.api;

import com.epam.esm.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository<K> {

    List<Order> findAll(Integer pageNum, Integer pageSize); // TODO delete me

    Optional<Order> findById(K id);

    Order create(Order order);

}
