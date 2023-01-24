package ru.nmorf.car.backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nmorf.car.backend.dao.repository.entity.AppUserEntity;

public interface IAppUserEntityRepo extends JpaRepository<AppUserEntity, Long> {

    AppUserEntity findByEmail(String email);

}
