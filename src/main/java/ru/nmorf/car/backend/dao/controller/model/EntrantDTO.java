package ru.nmorf.car.backend.dao.controller.model;

import lombok.Data;
import ru.nmorf.car.backend.security.type.Role;
import ru.nmorf.car.backend.security.type.Status;

@Data
public class EntrantDTO {
    private String name;
    private String email;
    private Role role;
    private Status status;
}
