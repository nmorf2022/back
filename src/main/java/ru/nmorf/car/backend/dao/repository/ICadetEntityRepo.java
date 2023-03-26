package ru.nmorf.car.backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nmorf.car.backend.dao.repository.model.CadetEntity;

public interface ICadetEntityRepo extends JpaRepository<CadetEntity, Long> {
    CadetEntity findByEmail(String email);
}
