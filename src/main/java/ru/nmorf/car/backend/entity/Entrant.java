package ru.nmorf.car.backend.entity;

import ru.nmorf.car.backend.security.type.Role;
import ru.nmorf.car.backend.security.type.Status;

public class Entrant extends SecurityUser {
    private String driverCategory;

    public Entrant(String name, String password, String email, Role role, Status status, String driverCategory) {
        super(name, password, email, role, status);
        this.driverCategory = driverCategory;
    }

    public String getDriverCategory() {
        return driverCategory;
    }

    public void setDriverCategory(String driverCategory) {
        this.driverCategory = driverCategory;
    }
}
