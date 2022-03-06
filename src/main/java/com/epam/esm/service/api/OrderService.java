package com.epam.esm.service.api;

import com.epam.esm.model.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> findByUserId(Long userId);

    OrderDto findById(Long id);

    OrderDto create(Long userId, List<Long> certificatesIds);
}
