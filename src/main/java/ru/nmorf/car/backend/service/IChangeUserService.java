package ru.nmorf.car.backend.service;

import ru.nmorf.car.backend.entity.SecurityUser;

public interface IChangeUserService {
    Integer changeEntrantToCadet(SecurityUser user);
}
