package com.epam.esm.service.impl;

import com.epam.esm.repository.Specification;
import com.epam.esm.repository.api.CertificateRepository;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.model.dto.CertificateDto;
import com.epam.esm.model.dto.UpdatingCertificateDto;
import com.epam.esm.model.entity.Certificate;
import com.epam.esm.model.entity.OrderType;
import com.epam.esm.model.entity.SortType;
import com.epam.esm.repository.specification.SpecificationCreator;
import com.epam.esm.service.api.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository<Long> certificateRepository;
    private final ConversionService conversionService;

    @Override
    @Transactional
    public CertificateDto create(UpdatingCertificateDto certificate) {
        Certificate createdCertificate = certificateRepository.create(certificate);
        return conversionService.convert(createdCertificate, CertificateDto.class);
    }

    @Override
    public List<CertificateDto> findAll(String tagName, String name, String description, SortType sort, OrderType order) {
        SpecificationCreator specificationCreator = new SpecificationCreator();
        Specification findSpecification =
                specificationCreator.createSpecification(name, description, tagName, sort, order);
        List<Certificate> certificates = certificateRepository.findBySpecification(findSpecification);
        return certificates.stream()
                .map(c -> conversionService.convert(c, CertificateDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CertificateDto findById(Long id) {
        Optional<Certificate> certificate = certificateRepository.findById(id);
        Certificate item = certificate.orElseThrow(
                () -> new EntityNotFoundException("Cannot find certificate with id " + id));
        return conversionService.convert(item, CertificateDto.class);
    }

    @Override
    @Transactional
    public CertificateDto update(Long updateId, UpdatingCertificateDto replacement) {
        Certificate updatedCertificate = certificateRepository.update(updateId, replacement);
        return conversionService.convert(updatedCertificate, CertificateDto.class);
    }

    @Override
    public CertificateDto delete(Long deleteId) {
        Certificate deletedCertificate = certificateRepository.delete(deleteId);
        return conversionService.convert(deletedCertificate, CertificateDto.class);
    }
}
