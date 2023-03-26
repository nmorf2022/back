package ru.nmorf.car.backend.dao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nmorf.car.backend.dao.controller.mapper.ISecurityUserMapper;
import ru.nmorf.car.backend.dao.controller.model.EntrantCreateDTO;
import ru.nmorf.car.backend.dao.controller.model.EntrantDTO;
import ru.nmorf.car.backend.dao.controller.model.InstructorCreateDTO;
import ru.nmorf.car.backend.dao.controller.model.InstructorDTO;
import ru.nmorf.car.backend.service.ICreateUserService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminControllerV1 {

    private final ICreateUserService createUserService;
    private final ISecurityUserMapper securityUserMapper;

    @Autowired
    public AdminControllerV1(ICreateUserService createUserService,
                             ISecurityUserMapper securityUserMapper) {
        this.createUserService = createUserService;
        this.securityUserMapper = securityUserMapper;
    }

    @PostMapping("/create/entrant")
    @PreAuthorize("hasAuthority('create:entrant')")
    @ResponseBody
    public EntrantDTO createEntrant(@RequestBody EntrantCreateDTO requestDTO) {
        return securityUserMapper
                .toEntrantDTO(createUserService
                        .createUser(securityUserMapper.toSecurityUser(requestDTO)));
    }

    @PostMapping("/create/instructor")
    @PreAuthorize("hasAuthority('create:instructor')")
    @ResponseBody
    public InstructorDTO createInstructor(@RequestBody InstructorCreateDTO requestDTO) {
        return securityUserMapper
                .toInstructorDTO(createUserService
                        .createUser(securityUserMapper.toSecurityUser(requestDTO)));
    }
}
