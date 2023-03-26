package ru.nmorf.car.backend.dao.controller.model;

import lombok.Data;
import ru.nmorf.car.backend.security.type.Role;

@Data
public class InstructorDTO {
    private String name;
    private String email;
    private Role role;
}
