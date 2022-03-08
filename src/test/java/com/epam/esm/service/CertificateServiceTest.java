package com.epam.esm.service;

import com.epam.esm.model.dto.CertificateDto;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.dto.UpdatingCertificateDto;
import com.epam.esm.model.entity.Certificate;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.repository.api.CertificateRepository;
import com.epam.esm.repository.api.TagRepository;
import com.epam.esm.repository.specification.SpecificationCreator;
import com.epam.esm.service.impl.CertificateServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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
class CertificateServiceTest {

    private CertificateRepository<Long> certificateRepository;

    private TagRepository<Long> tagRepository;

    @Autowired
    private ConversionService conversionService;

    private SpecificationCreator specificationCreator;

    private CertificateServiceImpl service;

    private MockitoSession session;

    @BeforeEach
    public void beforeMethod() {
        certificateRepository = mock(CertificateRepository.class);
        tagRepository = mock(TagRepository.class);
        specificationCreator = new SpecificationCreator();
        service = new CertificateServiceImpl(certificateRepository, tagRepository, conversionService,
                specificationCreator);
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
        Certificate createData = new Certificate();
        Set<Tag> tags = new HashSet<>();
        createData.setName("name");
        createData.setDescription("qwe");
        createData.setPrice(2345.0);
        createData.setDuration(234);
        createData.setTags(tags);
        UpdatingCertificateDto certificateDto = new UpdatingCertificateDto();
        Set<TagDto> tagDtos = new HashSet<>();
        certificateDto.setName("name");
        certificateDto.setDescription("qwe");
        certificateDto.setPrice(2345.0);
        certificateDto.setDuration(234);
        certificateDto.setTags(tagDtos);
        Certificate certificateToReturn = new Certificate();
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        certificateToReturn.setId(100L);
        certificateToReturn.setName("name");
        certificateToReturn.setDescription("qwe");
        certificateToReturn.setPrice(2345.0);
        certificateToReturn.setDuration(234);
        certificateToReturn.setCreateDate(tempDate);
        certificateToReturn.setLastUpdateDate(tempDate);
        certificateToReturn.setTags(tags);
        when(certificateRepository.create(createData))
                .thenReturn(certificateToReturn);
        CertificateDto expected = conversionService.convert(certificateToReturn, CertificateDto.class);
        CertificateDto actual = service.create(certificateDto);
        assertEquals(expected, actual);
        verify(certificateRepository, times(1)).create(createData);
    }

    @Test
    void findAll() {
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        Certificate certificateName = new Certificate();
        certificateName.setId(100L);
        certificateName.setName("name");
        certificateName.setDescription("qwe");
        certificateName.setPrice(2345.0);
        certificateName.setDuration(234);
        certificateName.setCreateDate(tempDate);
        certificateName.setLastUpdateDate(tempDate);
        Certificate certificateCert = new Certificate();
        certificateCert.setId(101L);
        certificateCert.setName("Cert");
        certificateCert.setDescription("qwe");
        certificateCert.setPrice(2345.0);
        certificateCert.setDuration(234);
        certificateCert.setCreateDate(tempDate);
        certificateCert.setLastUpdateDate(tempDate);
        Certificate certificateAnother = new Certificate();
        certificateAnother.setId(102L);
        certificateAnother.setName("Another");
        certificateAnother.setDescription("qwe");
        certificateAnother.setPrice(2345.0);
        certificateAnother.setDuration(234);
        certificateAnother.setCreateDate(tempDate);
        certificateAnother.setLastUpdateDate(tempDate);
        List<Certificate> certificateList = new ArrayList<>();
        Collections.addAll(certificateList, certificateName, certificateCert, certificateAnother);
        when(certificateRepository.findAll(any(), any(), any()))
                .thenReturn(certificateList);
        List<CertificateDto> expected = CertificateDto.toCertificateDtoList(certificateList);
        List<CertificateDto> actual = service.findAll(null, null, null);
        assertEquals(actual, expected);
        verify(certificateRepository, times(1)).findAll(any(), any(), any());
    }

    @Test
    void findByIdTest() {
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        Certificate certificate = new Certificate();
        certificate.setId(100L);
        certificate.setName("name");
        certificate.setDescription("qwe");
        certificate.setPrice(2345.0);
        certificate.setDuration(234);
        certificate.setCreateDate(tempDate);
        certificate.setLastUpdateDate(tempDate);
        when(certificateRepository.findById(100L))
                .thenReturn(Optional.of(certificate));
        CertificateDto expected = conversionService.convert(certificate, CertificateDto.class);
        CertificateDto actual = service.findById(100L);
        assertEquals(expected, actual);
        verify(certificateRepository, times(1)).findById(100L);
    }

    @Test
    void updateTest() {
        Certificate certificate = new Certificate();
        Set<Tag> tags = new HashSet<>();
        certificate.setName("abc");
        certificate.setDescription("qwe");
        certificate.setPrice(100.0);
        certificate.setPrice(100.0);
        certificate.setTags(tags);
        Certificate updatedCertificate = new Certificate();
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        updatedCertificate.setId(100L);
        updatedCertificate.setName("abc");
        updatedCertificate.setDescription("qwe");
        updatedCertificate.setPrice(100.0);
        updatedCertificate.setPrice(100.0);
        updatedCertificate.setCreateDate(tempDate);
        updatedCertificate.setLastUpdateDate(tempDate);
        updatedCertificate.setTags(tags);
        when(certificateRepository.update(100L, certificate))
                .thenReturn(updatedCertificate);
        UpdatingCertificateDto dto = new UpdatingCertificateDto();
        dto.setName("abc");
        dto.setDescription("qwe");
        dto.setPrice(100.0);
        dto.setPrice(100.0);
        Set<TagDto> tagDtos = new HashSet<>();
        dto.setTags(tagDtos);
        CertificateDto expected = conversionService.convert(updatedCertificate, CertificateDto.class);
        CertificateDto actual = service.update(100L, dto);
        assertEquals(expected, actual);
        verify(certificateRepository, times(1)).update(100L, certificate);
    }

    @Test
    void deleteTest() {
        Timestamp tempDate = Timestamp.valueOf(LocalDateTime.now());
        Certificate deletedCertificate = new Certificate();
        deletedCertificate.setId(100L);
        deletedCertificate.setName("qwe");
        deletedCertificate.setDescription("abc");
        deletedCertificate.setDuration(100);
        deletedCertificate.setPrice(15d);
        deletedCertificate.setCreateDate(tempDate);
        deletedCertificate.setLastUpdateDate(tempDate);
        when(certificateRepository.delete(100L))
                .thenReturn(deletedCertificate);
        CertificateDto expected = conversionService.convert(deletedCertificate, CertificateDto.class);
        CertificateDto actual = service.delete(100L);
        assertEquals(expected, actual);
        verify(certificateRepository, times(1)).delete(100L);
    }
}