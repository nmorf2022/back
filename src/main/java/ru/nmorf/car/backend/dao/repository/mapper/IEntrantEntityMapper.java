package ru.nmorf.car.backend.dao.repository.mapper;

import org.mapstruct.Mapper;
import ru.nmorf.car.backend.dao.repository.model.EntrantEntity;
import ru.nmorf.car.backend.entity.Entrant;

@Mapper(componentModel = "spring")
public abstract class IEntrantEntityMapper {

    public Entrant toEntrant(EntrantEntity user){
        return new Entrant(user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getRole(),
                user.getStatus(),
                user.getDriverCategory());
    }
}
