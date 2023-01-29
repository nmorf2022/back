package ru.nmorf.car.backend.dao.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nmorf.car.backend.dao.controller.mapper.IAuthMapper;
import ru.nmorf.car.backend.dao.controller.model.AuthRequestDTO;
import ru.nmorf.car.backend.dao.controller.model.TokensDTO;
import ru.nmorf.car.backend.security.JwtTokenProvider;
import ru.nmorf.car.backend.service.IAuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControllerV1 {

    private final IAuthMapper authMapper;
    private final IAuthService authService;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public AuthControllerV1(
            IAuthMapper authMapper,
            IAuthService authService,
            JwtTokenProvider jwtTokenProvider) {
        this.authMapper = authMapper;
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public TokensDTO login(@RequestBody AuthRequestDTO requestDTO) {
        return authMapper.
                toTokensDTO(authService
                        .login(authMapper.toAuthData(requestDTO)));
    }

    @PostMapping("/refresh")
    public TokensDTO refreshTokens(HttpServletRequest request) {
        return authMapper.
                toTokensDTO(authService
                        .refresh(jwtTokenProvider.resolveToken(request)));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        //TODO удалить записи из Redis
    }
}
