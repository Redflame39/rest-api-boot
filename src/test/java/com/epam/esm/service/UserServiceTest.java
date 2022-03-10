package com.epam.esm.service;

import com.epam.esm.model.dto.UpdatingUserDto;
import com.epam.esm.model.dto.UserDto;
import com.epam.esm.model.entity.User;
import com.epam.esm.repository.api.UserRepository;
import com.epam.esm.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private ConversionService conversionService;

    private UserRepository<Long> userRepository;

    private UserServiceImpl userService;

    private MockitoSession session;

    @BeforeEach
    public void beforeMethod() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(conversionService, userRepository);
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @AfterEach
    public void afterMethod() {
        session.finishMocking();
    }

    @Test
    void findAllTest() {
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("mail@mail");
        user1.setPassword("pass");
        user1.setFirstName("qwe");
        user1.setLastName("rty");
        user1.setCreateDate(tempDate);
        user1.setLastUpdateDate(tempDate);
        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("mail2@mail");
        user2.setPassword("pass2");
        user2.setFirstName("qwe2");
        user2.setLastName("rty2");
        user2.setCreateDate(tempDate);
        user2.setLastUpdateDate(tempDate);
        List<User> users = new ArrayList<>();
        Collections.addAll(users, user1, user2);
        when(userRepository.findAll(any(), any()))
                .thenReturn(users);
        List<UserDto> expected = UserDto.toUserDtoList(users);
        List<UserDto> actual = userService.findAll(0, 0);
        assertEquals(expected, actual);
        verify(userRepository, times(1)).findAll(0, 0);
    }

    @Test
    void findByIdTest() {
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        User userFound = new User();
        userFound.setId(1L);
        userFound.setEmail("mail@mail");
        userFound.setPassword("pass");
        userFound.setFirstName("qwe");
        userFound.setLastName("rty");
        userFound.setCreateDate(tempDate);
        userFound.setLastUpdateDate(tempDate);
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(userFound));
        UserDto expected = conversionService.convert(userFound, UserDto.class);
        UserDto actual = userService.findById(1L);
        assertEquals(expected, actual);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void createTest() {
        UpdatingUserDto dto = new UpdatingUserDto();
        dto.setEmail("mail@mail");
        dto.setPassword("pass");
        dto.setFirstName("qwe");
        dto.setLastName("rty");
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        User createdUser = new User();
        createdUser.setId(1L);
        createdUser.setEmail("mail@mail");
        createdUser.setPassword("pass");
        createdUser.setFirstName("qwe");
        createdUser.setLastName("rty");
        createdUser.setCreateDate(tempDate);
        createdUser.setLastUpdateDate(tempDate);
        User toPersistUser = new User();
        toPersistUser.setEmail("mail@mail");
        toPersistUser.setPassword("pass");
        toPersistUser.setFirstName("qwe");
        toPersistUser.setLastName("rty");
        when(userRepository.create(toPersistUser))
                .thenReturn(createdUser);
        UserDto expected = conversionService.convert(createdUser, UserDto.class);
        UserDto actual = userService.create(dto);
        assertEquals(expected, actual);
        verify(userRepository, times(1)).create(toPersistUser);
    }

    @Test
    void updateTest() {
        Long updateId = 1L;
        UpdatingUserDto dto = new UpdatingUserDto();
        dto.setEmail("mail_upd@mail");
        dto.setPassword("pass_upd");
        dto.setFirstName("qwe_upd");
        dto.setLastName("rty_upd");
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setEmail("mail@mail");
        updatedUser.setPassword("pass");
        updatedUser.setFirstName("qwe");
        updatedUser.setLastName("rty");
        updatedUser.setCreateDate(tempDate);
        updatedUser.setLastUpdateDate(tempDate);
        User toMergeUser = new User();
        toMergeUser.setEmail("mail_upd@mail");
        toMergeUser.setPassword("pass_upd");
        toMergeUser.setFirstName("qwe_upd");
        toMergeUser.setLastName("rty_upd");
        when(userRepository.update(updateId, toMergeUser))
                .thenReturn(updatedUser);
        UserDto expected = conversionService.convert(updatedUser, UserDto.class);
        UserDto actual = userService.update(updateId, dto);
        assertEquals(expected, actual);
        verify(userRepository, times(1)).update(updateId, toMergeUser);
    }

    @Test
    void deleteTest() {
        Long deleteId = 1L;
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        User deletedUser = new User();
        deletedUser.setId(1L);
        deletedUser.setEmail("mail@mail");
        deletedUser.setPassword("pass");
        deletedUser.setFirstName("qwe");
        deletedUser.setLastName("rty");
        deletedUser.setCreateDate(tempDate);
        deletedUser.setLastUpdateDate(tempDate);
        when(userRepository.delete(deleteId))
                .thenReturn(deletedUser);
        UserDto expected = conversionService.convert(deletedUser, UserDto.class);
        UserDto actual = userService.delete(deleteId);
        assertEquals(expected, actual);
        verify(userRepository, times(1)).delete(deleteId);
    }

}