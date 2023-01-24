package ru.nmorf.car.backend.dao.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


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
    @ManyToMany(fetch = FetchType.LAZY)
    @Column(name = "id")
    private Collection<RoleEntity> roles = new ArrayList<>();
}
