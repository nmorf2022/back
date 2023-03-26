package ru.nmorf.car.backend.dao.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nmorf.car.backend.dao.controller.model.EntrantDTO;
import ru.nmorf.car.backend.dao.controller.model.PatchEntrantDTO;
import ru.nmorf.car.backend.entity.Entrant;

@Mapper(componentModel = "spring")
public abstract class IEntrantMapper {

    @Mapping(target = "driver_category", source = "driverCategory")
    public abstract EntrantDTO toEntrantDTO(Entrant entrant);

    public Entrant toEntrant(PatchEntrantDTO patchEntrant){
        return new Entrant(patchEntrant.getName(),
                null,
                patchEntrant.getEmail(),
                null,
                null,
                patchEntrant.getDriver_category());
    }
}
