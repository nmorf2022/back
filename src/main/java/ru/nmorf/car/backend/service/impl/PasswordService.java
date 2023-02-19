package ru.nmorf.car.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nmorf.car.backend.dao.repository.IAppUserEntityRepo;
import ru.nmorf.car.backend.dao.repository.model.AppUserEntity;
import ru.nmorf.car.backend.entity.AuthData;
import ru.nmorf.car.backend.entity.PasswordChangeData;
import ru.nmorf.car.backend.exception.impl.InvalidPasswordException;
import ru.nmorf.car.backend.security.JwtTokenProvider;
import ru.nmorf.car.backend.service.IAuthService;
import ru.nmorf.car.backend.service.IPasswordService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class PasswordService implements IPasswordService {

    private final JwtTokenProvider jwtTokenProvider;
    private final IAuthService authService;
    private final IAppUserEntityRepo userEntityRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordService(JwtTokenProvider jwtTokenProvider,
                           IAuthService authService, IAppUserEntityRepo userEntityRepo,
                           PasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authService = authService;
        this.userEntityRepo = userEntityRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Map<String, String> changePassword(PasswordChangeData passwordChangeData,
                                              HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String email = jwtTokenProvider.getUsername(token);
        AppUserEntity user = userEntityRepo.findByEmail(email);
        if(passwordEncoder.matches(passwordChangeData.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordChangeData.getNewPassword()));
            userEntityRepo.save(user);
            authService.logoutAll(request);
            return authService.login(AuthData
                    .builder()
                    .email(email)
                    .password(passwordChangeData.getNewPassword())
                    .build());
        }
        else {
            throw new InvalidPasswordException();
        }
    }
}
