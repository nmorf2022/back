package ru.nmorf.car.backend.service;

import ru.nmorf.car.backend.entity.AuthData;

import java.util.Map;

public interface IAuthService {
    Map<String, String> login(AuthData authData);
    Map<String, String> refresh(String token);
}
