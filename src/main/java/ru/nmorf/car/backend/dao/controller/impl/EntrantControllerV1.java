package ru.nmorf.car.backend.dao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nmorf.car.backend.dao.controller.mapper.IEntrantMapper;
import ru.nmorf.car.backend.dao.controller.model.EntrantDTO;
import ru.nmorf.car.backend.dao.controller.model.PatchEntrantDTO;
import ru.nmorf.car.backend.service.IEntrantService;

@RestController
@RequestMapping("/api/v1/entrant")
public class EntrantControllerV1 {

    private final IEntrantMapper entrantMapper;
    private final IEntrantService entrantService;

    @Autowired
    public EntrantControllerV1(IEntrantMapper entrantMapper,
                               IEntrantService entrantService) {
        this.entrantMapper = entrantMapper;
        this.entrantService = entrantService;
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('change:entrant')")
    @ResponseBody
    public EntrantDTO changeEntrant(@RequestBody PatchEntrantDTO requestDTO) {
        return entrantMapper
                .toEntrantDTO(entrantService
                        .patchEntrant(entrantMapper.toEntrant(requestDTO)));
    }
}
