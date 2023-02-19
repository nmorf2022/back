package ru.nmorf.car.backend.service;

import ru.nmorf.car.backend.entity.AuthData;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IAuthService {
    Map<String, String> login(AuthData authData);
    Map<String, String> refresh(HttpServletRequest request);
    void logout(HttpServletRequest request);
    void logoutAll(HttpServletRequest request);
}
