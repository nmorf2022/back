package ru.nmorf.car.backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.nmorf.car.backend.dao.repository.model.AppUserEntity;
import ru.nmorf.car.backend.security.type.Role;

public interface IAppUserEntityRepo extends JpaRepository<AppUserEntity, Long> {

    AppUserEntity findByEmail(String email);


    @Modifying
    @Query("update AppUserEntity u set u.role = ?1 where u.email = ?2")
    int setRole(Role role, String email);
}
