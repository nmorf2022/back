package ru.nmorf.car.backend.dao.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("ENTRANT")
public class EntrantEntity extends AppUserEntity {
    @Column(name = "driver_category")
    private String driverCategory;
}
