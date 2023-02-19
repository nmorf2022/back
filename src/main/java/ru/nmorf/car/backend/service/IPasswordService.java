package ru.nmorf.car.backend.service;

import ru.nmorf.car.backend.entity.PasswordChangeData;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IPasswordService {
    Map<String, String> changePassword(PasswordChangeData passwordChangeData,
                                       HttpServletRequest request);
}
