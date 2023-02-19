package ru.nmorf.car.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthData {
    private String email;
    private String password;
}
