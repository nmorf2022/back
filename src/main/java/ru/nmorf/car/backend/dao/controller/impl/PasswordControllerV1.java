package ru.nmorf.car.backend.dao.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nmorf.car.backend.dao.controller.mapper.IAuthMapper;
import ru.nmorf.car.backend.dao.controller.mapper.IPasswordMapper;
import ru.nmorf.car.backend.dao.controller.model.ChangePasswordDTO;
import ru.nmorf.car.backend.dao.controller.model.TokensDTO;
import ru.nmorf.car.backend.service.IPasswordService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/password")
public class PasswordControllerV1 {

    private final IPasswordService passwordService;
    private final IAuthMapper authMapper;
    private final IPasswordMapper passwordMapper;

    @Autowired
    public PasswordControllerV1(IPasswordService passwordService,
                                IAuthMapper authMapper,
                                IPasswordMapper passwordMapper) {
        this.passwordService = passwordService;
        this.authMapper = authMapper;
        this.passwordMapper = passwordMapper;
    }

    @PostMapping("/change")
    @PreAuthorize("hasAuthority('password:change')")
    public TokensDTO changePassword(@RequestBody ChangePasswordDTO requestDTO,
                                    HttpServletRequest request) {
        return authMapper
                .toTokensDTO(passwordService
                        .changePassword(passwordMapper.toPasswordChangeData(requestDTO), request));
    }

}
