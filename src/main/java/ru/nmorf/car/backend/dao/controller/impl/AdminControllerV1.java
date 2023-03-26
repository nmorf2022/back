package ru.nmorf.car.backend.dao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nmorf.car.backend.dao.controller.mapper.ISecurityUserMapper;
import ru.nmorf.car.backend.dao.controller.model.*;
import ru.nmorf.car.backend.service.IChangeUserService;
import ru.nmorf.car.backend.service.ICreateUserService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminControllerV1 {

    private final ICreateUserService createUserService;
    private final IChangeUserService changeUserService;
    private final ISecurityUserMapper securityUserMapper;

    @Autowired
    public AdminControllerV1(ICreateUserService createUserService,
                             IChangeUserService changeUserService,
                             ISecurityUserMapper securityUserMapper) {
        this.createUserService = createUserService;
        this.changeUserService = changeUserService;
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

    @PostMapping("/change/entrant")
    @PreAuthorize("hasAuthority('change:entrant')")
    @ResponseBody
    public ResponseEntity<String> changeEntrant(@RequestBody ChangeEntrantDTO requestDTO) {
        return changeUserService
                        .changeEntrantToCadet(securityUserMapper.toSecurityUser(requestDTO)) == 1 ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
