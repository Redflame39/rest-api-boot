package com.epam.esm.repository.impl;

import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.repository.Specification;
import com.epam.esm.repository.api.CertificateRepository;
import com.epam.esm.model.dto.UpdatingCertificateDto;
import com.epam.esm.model.entity.Certificate;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CertificateRepositoryImpl implements CertificateRepository<Long> {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ConversionService conversionService;

    @Override
    public List<Certificate> findBySpecification(Specification specification) { // TODO add use of specification
        return entityManager.createQuery("from Certificate", Certificate.class).getResultList();
    }

    @Override
    public Optional<Certificate> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Certificate.class, id));
    }

    @Override
    @Transactional
    public Certificate create(UpdatingCertificateDto certificate) {
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());
        Certificate newCertificate = new Certificate();
        newCertificate.setName(certificate.getName());
        newCertificate.setDescription(certificate.getDescription());
        newCertificate.setPrice(certificate.getPrice());
        newCertificate.setDuration(certificate.getDuration());
        newCertificate.setCreateDate(currentTime);
        newCertificate.setLastUpdateDate(currentTime);
        //Set<Tag> tags = conversionService.convert(certificate.getTags(), Tag.class);
        Set<Tag> tags = TagDto.toTagSet(certificate.getTags());
        newCertificate.setTags(tags);
        entityManager.persist(newCertificate);
        return newCertificate;
    }

    @Override
    @Transactional
    public Certificate update(Long updateId, UpdatingCertificateDto replacement) {
        Certificate certificate = entityManager.find(Certificate.class, updateId);
        entityManager.detach(certificate);
        certificate.setName(replacement.getName());
        certificate.setDescription(replacement.getDescription());
        certificate.setDuration(replacement.getDuration());
        certificate.setPrice(replacement.getPrice());
        if (replacement.getTags() != null) {
            Set<Tag> tags = TagDto.toTagSet(replacement.getTags());
            certificate.setTags(tags);
        }
        entityManager.merge(certificate);
        return certificate;
    }

    @Override
    @Transactional
    public Certificate delete(Long deleteId) {
        Certificate certificate = entityManager.find(Certificate.class, deleteId);
        entityManager.remove(certificate);
        return certificate;
    }
}
