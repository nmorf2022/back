package ru.nmorf.car.backend.dao.controller.model;

import lombok.Data;

@Data
public class PatchEntrantDTO {

    private String name;
    private String email;
    private String driver_category;
}
