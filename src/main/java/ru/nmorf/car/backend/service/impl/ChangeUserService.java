package ru.nmorf.car.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nmorf.car.backend.dao.repository.IAppUserEntityRepo;
import ru.nmorf.car.backend.dao.repository.mapper.IAppUserEntityMapper;
import ru.nmorf.car.backend.entity.SecurityUser;
import ru.nmorf.car.backend.exception.impl.SwitchToCadetNotAvailableException;
import ru.nmorf.car.backend.security.type.Role;
import ru.nmorf.car.backend.security.type.Status;
import ru.nmorf.car.backend.service.IChangeUserService;

@Service
@Transactional
public class ChangeUserService implements IChangeUserService {

    private final IAppUserEntityRepo userEntityRepo;
    private final IAppUserEntityMapper userEntityMapper;

    @Autowired
    public ChangeUserService(IAppUserEntityRepo userEntityRepo,
                             IAppUserEntityMapper userEntityMapper) {
        this.userEntityRepo = userEntityRepo;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public Integer changeEntrantToCadet(SecurityUser user) {
        SecurityUser changeUser = userEntityMapper
                .toSecurityUser(userEntityRepo.findByEmail(user.getEmail()));
        if(changeUser == null ||
                changeUser.getRole() != Role.ENTRANT ||
                changeUser.getStatus() != Status.ACTIVE) {
            throw new SwitchToCadetNotAvailableException();
        }
        return userEntityRepo.setRole(Role.CADET, changeUser.getEmail());
    }
}
