package com.epam.esm.service.impl;

import com.epam.esm.converter.TagToTagDtoConverter;
import com.epam.esm.repository.api.TagRepository;
import com.epam.esm.exception.EntityNotCreatedException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.EntityNotUpdatedException;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.api.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository<Long> repository;

    @Autowired
    public TagServiceImpl(TagRepository<Long> dao) {
        this.repository = dao;
    }

    @Override
    public TagDto create(TagDto tag) {
        Long id = repository.create(tag);
        Optional<Tag> created = repository.findById(id);
        if (!created.isPresent()) {
            throw new EntityNotCreatedException("Cannot find created tag, id " + id);
        }
        TagToTagDtoConverter converter = new TagToTagDtoConverter();
        return converter.convert(created.get());
    }

    @Override
    public List<TagDto> findAll() {
        List<Tag> tags = repository.findAll();
        TagToTagDtoConverter converter = new TagToTagDtoConverter();
        return tags.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<TagDto> findByCertificateId(Long id) {
        List<Tag> tags = repository.findByCertificateId(id);
        TagToTagDtoConverter converter = new TagToTagDtoConverter();
        return tags.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findById(Long id) {
        Optional<Tag> tag = repository.findById(id);
        Tag item = tag.orElseThrow(
                () -> new EntityNotFoundException("Tag with id " + id + " cannot be found"));
        TagToTagDtoConverter converter = new TagToTagDtoConverter();
        return converter.convert(item);
    }

    @Override
    public TagDto delete(Long deleteId) {
        TagDto old = findById(deleteId);
        boolean deleted = repository.delete(deleteId);
        if (!deleted) {
            throw new EntityNotUpdatedException("Tag deleting wasn't carried out. id " + deleteId);
        }
        return old;
    }
}
