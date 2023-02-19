package ru.nmorf.car.backend.dao.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nmorf.car.backend.dao.controller.model.ChangePasswordDTO;
import ru.nmorf.car.backend.entity.PasswordChangeData;

@Mapper(componentModel = "spring")
public interface IPasswordMapper {


    @Mapping(target = "oldPassword", source = "old_password")
    @Mapping(target = "newPassword", source = "new_password")
    PasswordChangeData toPasswordChangeData(ChangePasswordDTO password);
}
