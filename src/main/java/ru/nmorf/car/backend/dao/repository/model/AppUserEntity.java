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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("APP_USER")
public class AppUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "app_user_generator")
    @SequenceGenerator(name = "app_user_generator",
                        sequenceName = "app_user_id_seq", allocationSize = 1)
    @Column(name = "id",
            updatable = false,
            nullable = false)
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
    @Column(name = "role",
            nullable = false)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_status",
            nullable = false)
    private Status status;

}
