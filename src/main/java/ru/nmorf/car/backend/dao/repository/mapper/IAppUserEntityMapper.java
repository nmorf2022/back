package ru.nmorf.car.backend.dao.repository.mapper;

import org.mapstruct.Mapper;
import ru.nmorf.car.backend.dao.repository.model.AppUserEntity;
import ru.nmorf.car.backend.entity.SecurityUser;

@Mapper(componentModel = "spring")
public interface IAppUserEntityMapper {

    SecurityUser toSecurityUser(AppUserEntity user);
    AppUserEntity toAppUserEntity(SecurityUser user);
}
