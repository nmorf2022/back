package ru.nmorf.car.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nmorf.car.backend.dao.repository.IAppUserEntityRepo;
import ru.nmorf.car.backend.dao.repository.model.AppUserEntity;
import ru.nmorf.car.backend.util.SecurityUser;

import java.util.Optional;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IAppUserEntityRepo repository;

    @Autowired
    public UserDetailsServiceImpl(IAppUserEntityRepo repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUserEntity user = Optional.ofNullable(repository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        return SecurityUser.fromUser(user);
    }
}
