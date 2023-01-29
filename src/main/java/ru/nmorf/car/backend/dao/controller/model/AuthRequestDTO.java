package ru.nmorf.car.backend.dao.controller.model;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;
}
