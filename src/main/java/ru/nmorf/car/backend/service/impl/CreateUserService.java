package ru.nmorf.car.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nmorf.car.backend.dao.repository.IAppUserEntityRepo;
import ru.nmorf.car.backend.dao.repository.mapper.IAppUserEntityMapper;
import ru.nmorf.car.backend.entity.SecurityUser;
import ru.nmorf.car.backend.exception.impl.UserEmailAlreadyExistsException;
import ru.nmorf.car.backend.service.ICreateUserService;

@Service
public class CreateUserService implements ICreateUserService {

    private final IAppUserEntityRepo userEntityRepo;
    private final PasswordEncoder passwordEncoder;
    private final IAppUserEntityMapper userEntityMapper;

    @Autowired
    public CreateUserService(IAppUserEntityRepo userEntityRepo,
                             PasswordEncoder passwordEncoder,
                             IAppUserEntityMapper userEntityMapper) {
        this.userEntityRepo = userEntityRepo;
        this.passwordEncoder = passwordEncoder;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    @Transactional
    public SecurityUser createUser(SecurityUser user) {
        if(userEntityRepo.findByEmail(user.getEmail()) != null) {
            throw new UserEmailAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntityRepo.save(userEntityMapper.toAppUserEntity(user));
        userEntityRepo.setRole(user.getRole().name(), user.getEmail());
        return user;
    }
}
