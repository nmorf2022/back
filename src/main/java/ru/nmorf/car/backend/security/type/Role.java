package ru.nmorf.car.backend.security.type;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    CADET(Set.of(Permission.PASSWORD_CHANGE)),
    INSTRUCTOR(Set.of(Permission.PASSWORD_CHANGE)),
    ENTRANT(Set.of(Permission.PASSWORD_CHANGE,
            Permission.CHANGE_ENTRANT)),
    ADMIN(Set.of(Permission.PASSWORD_CHANGE,
            Permission.CREATE_ENTRANT,
            Permission.CREATE_INSTRUCTOR,
            Permission.CHANGE_ENTRANT_TO_CADET));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
