package com.epam.esm.service;

import com.epam.esm.model.dto.OrderDto;
import com.epam.esm.model.entity.Certificate;
import com.epam.esm.model.entity.Order;
import com.epam.esm.model.entity.User;
import com.epam.esm.repository.api.CertificateRepository;
import com.epam.esm.repository.api.OrderRepository;
import com.epam.esm.repository.api.UserRepository;
import com.epam.esm.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OrderServiceTest {

    private OrderRepository<Long> orderRepository;

    private UserRepository<Long> userRepository;

    private CertificateRepository<Long> certificateRepository;

    @Autowired
    private ConversionService conversionService;

    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        userRepository = mock(UserRepository.class);
        certificateRepository = mock(CertificateRepository.class);
        orderService = new OrderServiceImpl(orderRepository, userRepository, certificateRepository, conversionService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByUserIdTest() {
        Long userId = 10L;
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        User user = new User();
        user.setId(userId);
        Order o1 = new Order();
        o1.setId(1L);
        o1.setUser(user);
        o1.setCreateDate(tempDate);
        o1.setCertificates(new HashSet<>());
        Order o2 = new Order();
        o2.setId(2L);
        o2.setUser(user);
        o2.setCreateDate(tempDate);
        o2.setCertificates(new HashSet<>());
        Set<Order> orders = new HashSet<>();
        Collections.addAll(orders, o1, o2);
        user.setOrders(orders);
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(user));
        List<OrderDto> expected = OrderDto.toOrderDtoList(new ArrayList<>(orders));
        List<OrderDto> actual = orderService.findByUserId(userId);
        assertEquals(expected, actual);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void findByIdTest() {
        Long id = 1L;
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        Order order = new Order();
        order.setPrice(123.0);
        order.setUser(new User());
        order.setCertificates(new HashSet<>());
        order.setCreateDate(tempDate);
        when(orderRepository.findById(id))
                .thenReturn(Optional.of(order));
        OrderDto expected = conversionService.convert(order, OrderDto.class);
        OrderDto actual = orderService.findById(id);
        assertEquals(expected, actual);
        verify(orderRepository, times(1)).findById(id);
    }

    @Test
    void createTest() {
        Long userId = 10L;
        Certificate certificate1 = new Certificate();
        Long certId1 = 1L;
        Double price1 = 1D;
        certificate1.setId(certId1);
        certificate1.setPrice(price1);
        Certificate certificate2 = new Certificate();
        Long certId2 = 2L;
        Double price2 = 2D;
        certificate2.setId(certId2);
        certificate2.setPrice(price2);
        Certificate certificate3 = new Certificate();
        Long certId3 = 3L;
        Double price3 = 3D;
        certificate3.setId(certId3);
        certificate3.setPrice(price3);
        List<Long> certificatesIds = new ArrayList<>();
        Set<Certificate> certificates = new HashSet<>();
        Collections.addAll(certificatesIds, certId1, certId2, certId3);
        Collections.addAll(certificates, certificate1, certificate2, certificate3);
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(user));
        when(certificateRepository.findById(certId1))
                .thenReturn(Optional.of(certificate1));
        when(certificateRepository.findById(certId2))
                .thenReturn(Optional.of(certificate2));
        when(certificateRepository.findById(certId3))
                .thenReturn(Optional.of(certificate3));
        Order order = new Order();
        order.setPrice(price1 + price2 + price3);
        order.setCertificates(certificates);
        order.setUser(user);
        Order createdOrder = new Order();
        Long orderId = 100L;
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        createdOrder.setId(orderId);
        createdOrder.setPrice(price1 + price2 + price3);
        createdOrder.setCertificates(certificates);
        createdOrder.setUser(user);
        createdOrder.setCreateDate(tempDate);
        when(orderRepository.create(order))
                .thenReturn(createdOrder);
        OrderDto expected = conversionService.convert(createdOrder, OrderDto.class);
        OrderDto actual = orderService.create(userId, certificatesIds);
        assertEquals(expected, actual);
        verify(orderRepository, times(1)).create(order);
        verify(userRepository, times(1)).findById(userId);
        verify(certificateRepository, times(3))
                .findById(argThat(arg -> arg >= 1 && arg <= 3));
    }
}