package ru.nmorf.car.backend.dao.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nmorf.car.backend.dao.controller.model.EntrantCreateDTO;
import ru.nmorf.car.backend.dao.controller.model.EntrantDTO;
import ru.nmorf.car.backend.entity.SecurityUser;

@Mapper(componentModel = "spring")
public interface ISecurityUserMapper {

    @Mapping(target = "role", constant = "ENTRANT")
    @Mapping(target = "status", constant = "ACTIVE")
    SecurityUser toSecurityUser(EntrantCreateDTO entrant);

    EntrantDTO toEntrantDTO(SecurityUser user);
}
