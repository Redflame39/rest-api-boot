package com.epam.esm.controller;

import com.epam.esm.model.dto.CertificateDto;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.dto.UpdatingCertificateDto;
import com.epam.esm.model.entity.OrderType;
import com.epam.esm.model.entity.SortType;
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
    private final TagService tagService;

    @Autowired
    public CertificateController(CertificateService certificateService, TagService tagService) {
        this.certificateService = certificateService;
        this.tagService = tagService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CertificateDto> read(@RequestParam(value = "tag", required = false) String tag,
                                     @RequestParam(value = "sort_by", required = false) String sortBy,
                                     @RequestParam(value = "order", required = false) String order,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "description", required = false) String description) {
        SortType sortType = SortType.parseSortType(sortBy);
        OrderType orderType = OrderType.parseOrderType(order);
        return certificateService.findAll(tag, name, description, sortType, orderType);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto read(@PathVariable Long id) {
        return certificateService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CertificateDto create(@RequestBody UpdatingCertificateDto certificate) {
        CertificateDto created = certificateService.create(certificate);
        List<TagDto> tags = tagService.findByCertificateId(created.getId());
        created.setTags(tags);
        return created;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto update(@PathVariable Long id, @RequestBody UpdatingCertificateDto certificateDto) {
        return certificateService.update(id, certificateDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto delete(@PathVariable Long id) {
        return certificateService.delete(id);
    }
}
