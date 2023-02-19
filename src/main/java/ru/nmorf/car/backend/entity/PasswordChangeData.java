package ru.nmorf.car.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordChangeData {
    String oldPassword;
    String newPassword;
}
