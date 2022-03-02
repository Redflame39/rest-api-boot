package com.epam.esm.repository.impl;

import com.epam.esm.model.dto.OrderDto;
import com.epam.esm.model.entity.Order;
import com.epam.esm.repository.api.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository<Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("from Order", Order.class).getResultList();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    @Transactional
    public Order create(Order order) {
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());
        order.setCreateDate(currentTime);
        entityManager.persist(order);
        return order;
    }
}
