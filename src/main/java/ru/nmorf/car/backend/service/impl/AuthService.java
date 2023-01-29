package ru.nmorf.car.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nmorf.car.backend.dao.repository.IAppUserEntityRepo;
import ru.nmorf.car.backend.dao.repository.mapper.IAppUserEntityMapper;
import ru.nmorf.car.backend.entity.AuthData;
import ru.nmorf.car.backend.entity.SecurityUser;
import ru.nmorf.car.backend.exception.impl.TokenIsNotRefreshTypeException;
import ru.nmorf.car.backend.security.JwtTokenProvider;
import ru.nmorf.car.backend.service.IAuthService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final IAppUserEntityRepo repo;
    private final IAppUserEntityMapper mapper;
    private final StringRedisTemplate redisTemplate;
    @Value("${redis.jwt_table}")
    private String redisTable;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                       JwtTokenProvider jwtTokenProvider,
                       IAppUserEntityRepo repo,
                       IAppUserEntityMapper mapper, StringRedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.repo = repo;
        this.mapper = mapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Map<String, String> login(AuthData authData) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authData.getEmail(), authData.getPassword()));
        return createTokens(authData.getEmail());
    }

    @Override
    public Map<String, String> refresh(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if(jwtTokenProvider.isRefreshToken(token)) {
            String username = jwtTokenProvider.getUsername(token);
            String key =  redisTable + ":" + username;
            redisTemplate.delete(key);
            return createTokens(username);
        } else {
            throw new TokenIsNotRefreshTypeException();
        }
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        String key =  redisTable + ":" + username;
        redisTemplate.delete(key);
    }

    private Map<String, String> createTokens(String username) {
        SecurityUser user = Optional
                .ofNullable(mapper.toSecurityUser(repo.findByEmail(username)))
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        Map<String, String> response = jwtTokenProvider.createTokens(user.getEmail(), user.getRole());
        response.put("email", user.getEmail());
        return response;
    }
}
