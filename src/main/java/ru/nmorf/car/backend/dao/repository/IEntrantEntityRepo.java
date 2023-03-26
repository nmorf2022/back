package ru.nmorf.car.backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.nmorf.car.backend.dao.repository.model.EntrantEntity;

public interface IEntrantEntityRepo extends JpaRepository<EntrantEntity, Long> {
    EntrantEntity findByEmail(String email);

    @Modifying
    @Query(value = "update app_user set name = ?1, driver_category = ?2 where email = ?3", nativeQuery = true)
    int patch(String name, String driverCategory, String email);
}
