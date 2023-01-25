package ru.nmorf.car.backend.dao.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nmorf.car.backend.security.type.Role;
import ru.nmorf.car.backend.security.type.Status;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "app_user")
public class AppUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",
            nullable = false)
    private String name;
    @Column(name = "password",
            nullable = false)
    private String password;
    @Column(name = "email",
            nullable = false,
            unique = true)
    private String email;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_status")
    private Status status;

}
