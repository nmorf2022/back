package ru.nmorf.car.backend.dao.controller.model;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    String old_password;
    String new_password;
}
