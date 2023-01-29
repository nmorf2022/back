package ru.nmorf.car.backend.util;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.nmorf.car.backend.dao.repository.model.AppUserEntity;
import ru.nmorf.car.backend.security.type.Status;

public class SecurityUser{

    public static UserDetails fromUser(AppUserEntity user){
        return new User(
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getRole().getAuthorities()
        );
    }
}
