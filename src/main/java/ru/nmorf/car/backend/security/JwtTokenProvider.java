package ru.nmorf.car.backend.security;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.nmorf.car.backend.security.type.Role;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    @Value("${jwt.refresh_token_ttl}")
    private Long refreshTokenTTLInSeconds;
    @Value("${jwt.access_token_ttl}")
    private Long accessTokenTTLInSeconds;
    @Value("${jwt.secret}")
    private String securityKey;
    @Value("${jwt.header}")
    private String authHeader;

    @Autowired
    public JwtTokenProvider(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        securityKey = Base64.getEncoder().encodeToString(securityKey.getBytes());
    }

    public Map<String, String> createTokens(String username, Role role) {
        Map<String, String> tokens = new HashMap<>();
        List<String> consumers = Arrays.asList("car_frontend_app", "car_mobile_app");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String audience = "";
        try {
            audience = mapper.writeValueAsString(consumers);
        } catch (JsonProcessingException e) {
            //TODO добавить логирование
        }
        Date now  = new Date();
        Date refreshExpiration = new Date(now.getTime() + refreshTokenTTLInSeconds * 1000);
        Date accessExpiration = new Date(now.getTime() + accessTokenTTLInSeconds * 1000);
        Claims claims = Jwts
                .claims()
                .setSubject(username)
                .setAudience(audience)
                .setIssuedAt(now);
        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(refreshExpiration)
                .signWith(SignatureAlgorithm.HS256, securityKey)
                .compact();
        tokens.put("refresh_token", refreshToken);
        claims.put("role", role.name());
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(accessExpiration)
                .signWith(SignatureAlgorithm.HS256, securityKey)
                .compact();
        tokens.put("access_token", accessToken);
        return tokens;
    }

    public boolean isTokenValid(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            //TODO log
        }
        return false;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserDetails details = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(securityKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Optional<String> resolveToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(authHeader));
    }
}
