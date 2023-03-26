package ru.nmorf.car.backend.dao.controller.model;

import lombok.Data;

@Data
public class EntrantCreateDTO {
    String email;
    String name;
    String password;
}
