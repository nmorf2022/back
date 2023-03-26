package ru.nmorf.car.backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nmorf.car.backend.dao.repository.model.EntrantEntity;

public interface IEntrantEntityRepo extends JpaRepository<EntrantEntity, Long> {
}
