package com.epam.esm.service;

import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.repository.api.TagRepository;
import com.epam.esm.service.impl.TagServiceImpl;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TagServiceTest {

    @Autowired
    private ConversionService conversionService;

    private TagRepository<Long> tagRepository;

    private TagServiceImpl service;

    private MockitoSession session;

    @BeforeEach
    public void beforeMethod() {
        tagRepository = mock(TagRepository.class);
        service = new TagServiceImpl(tagRepository, conversionService);
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @AfterEach
    public void afterMethod() {
        session.finishMocking();
    }

    @Test
    void createTest() {
        Tag tag = new Tag();
        tag.setId(100L);
        tag.setName("name");
        TagDto tagDto = conversionService.convert(tag, TagDto.class);
        when(tagRepository.create(tagDto))
                .thenReturn(tag);
        TagDto expected = tagDto;
        TagDto actual = service.create(tagDto);
        assertEquals(expected, actual);
        verify(tagRepository, times(1)).create(tagDto);
    }

    @Test
    void findAllTest() {
        Tag tagName = new Tag();
        tagName.setName("name");
        Tag tag = new Tag();
        tag.setName("tag");
        Tag tagQwe = new Tag();
        tagQwe.setName("qwe");
        List<Tag> tags = new ArrayList<>();
        Collections.addAll(tags, tag, tagName, tagQwe);
        when(tagRepository.findAll(any(), any()))
                .thenReturn(tags);
        List<TagDto> expected = TagDto.toTagDtoList(tags);
        List<TagDto> actual = service.findAll(0, 0);
        assertEquals(expected, actual);
        verify(tagRepository, times(1)).findAll(0, 0);
    }

    @Test
    void findByCertificateIdTest() {
        Tag tag1 = new Tag();
        tag1.setName("name");

        Tag tag2 = new Tag();
        tag2.setName("Tag");

        List<Tag> tags = new ArrayList<>();
        Collections.addAll(tags, tag1, tag2);
        when(tagRepository.findByCertificateId(100L))
                .thenReturn(tags);
        List<TagDto> expected = TagDto.toTagDtoList(tags);
        List<TagDto> actual = service.findByCertificateId(100L);
        assertEquals(expected, actual);
        verify(tagRepository, times(1)).findByCertificateId(100L);
    }

    @Test
    void findByIdTest() {
        Tag tag = new Tag();
        tag.setId(100L);
        tag.setName("name");
        when(tagRepository.findById(100L))
                .thenReturn(Optional.of(tag));
        TagDto expected = conversionService.convert(tag, TagDto.class);
        TagDto actual = service.findById(100L);
        assertEquals(expected, actual);
        verify(tagRepository, times(1)).findById(100L);
    }

    @Test
    void deleteTest() {
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName("name");
        when(tagRepository.delete(1L))
                .thenReturn(tag);
        TagDto expected = conversionService.convert(tag, TagDto.class);
        TagDto actual = service.delete(1L);
        assertEquals(expected, actual);
        verify(tagRepository, times(1)).delete(1L);
    }
}