package ru.nmorf.car.backend.dao.controller.model;

import lombok.Data;

@Data
public class TokensDTO {
    private String access_token;
    private String refresh_token;
    private String email;
    private String role;
}
