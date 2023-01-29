package ru.nmorf.car.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nmorf.car.backend.dao.repository.IAppUserEntityRepo;
import ru.nmorf.car.backend.dao.repository.mapper.IAppUserEntityMapper;
import ru.nmorf.car.backend.entity.AuthData;
import ru.nmorf.car.backend.entity.SecurityUser;
import ru.nmorf.car.backend.security.JwtTokenProvider;
import ru.nmorf.car.backend.service.IAuthService;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final IAppUserEntityRepo repo;
    private final IAppUserEntityMapper mapper;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                       JwtTokenProvider jwtTokenProvider,
                       IAppUserEntityRepo repo,
                       IAppUserEntityMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Map<String, String> login(AuthData authData) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authData.getEmail(), authData.getPassword()));
        SecurityUser user = Optional
                .ofNullable(mapper.toSecurityUser(repo.findByEmail(authData.getEmail())))
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        Map<String, String> response = jwtTokenProvider.createTokens(user.getEmail(), user.getRole());
        response.put("email", user.getEmail());
        return response;
    }
}
