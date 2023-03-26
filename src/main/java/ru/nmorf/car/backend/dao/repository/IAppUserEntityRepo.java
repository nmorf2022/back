package ru.nmorf.car.backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.nmorf.car.backend.dao.repository.model.AppUserEntity;

public interface IAppUserEntityRepo extends JpaRepository<AppUserEntity, Long> {

    AppUserEntity findByEmail(String email);


    @Modifying
    @Query(value = "update app_user set role = ?1, dtype = ?1 where email = ?2", nativeQuery = true)
    int setRole(String role, String email);
}
