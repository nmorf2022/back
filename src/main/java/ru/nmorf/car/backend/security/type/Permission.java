package ru.nmorf.car.backend.security.type;

public enum Permission {
    PASSWORD_CHANGE("password:change"),
    CREATE_INSTRUCTOR("create:instructor"),
    CREATE_ENTRANT("create:entrant"),
    CHANGE_ENTRANT_TO_CADET("change:entrant_to_cadet"),
    CHANGE_ENTRANT("change:entrant");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
