package com.epam.esm.controller;

import com.epam.esm.model.dto.CertificateDto;
import com.epam.esm.model.dto.CertificatesQueryDto;
import com.epam.esm.model.dto.UpdatingCertificateDto;
import com.epam.esm.service.api.CertificateService;
import com.epam.esm.service.api.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService, TagService tagService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CertificateDto> read(@RequestBody(required = false) CertificatesQueryDto certificatesQueryDto) {
        return certificateService.findAll(certificatesQueryDto);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto read(@PathVariable Long id) {
        return certificateService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CertificateDto create(@RequestBody UpdatingCertificateDto certificate) {
        return certificateService.create(certificate);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto update(@PathVariable Long id, @RequestBody UpdatingCertificateDto certificateDto) {
        return certificateService.update(id, certificateDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto updatePrice(@PathVariable Long id, @RequestBody Double price) {
        return certificateService.updatePrice(id, price);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto delete(@PathVariable Long id) {
        return certificateService.delete(id);
    }
}
