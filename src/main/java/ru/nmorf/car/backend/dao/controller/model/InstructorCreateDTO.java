package ru.nmorf.car.backend.dao.controller.model;

import lombok.Data;

@Data
public class InstructorCreateDTO {
    String email;
    String name;
    String password;
}
