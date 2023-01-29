package ru.nmorf.car.backend.service;

import ru.nmorf.car.backend.entity.AuthData;

import java.util.Map;
import java.util.Optional;

public interface IAuthService {
    Map<String, String> login(AuthData authData);
    Map<String, String> refresh(Optional<String> token);
}
