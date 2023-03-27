package ru.nmorf.car.backend.dao.controller.mapper;

import org.mapstruct.Mapper;
import ru.nmorf.car.backend.dao.controller.model.AuthRequestDTO;
import ru.nmorf.car.backend.dao.controller.model.TokensDTO;
import ru.nmorf.car.backend.entity.AuthData;
import ru.nmorf.car.backend.exception.impl.TokenValidationException;

import java.util.Map;

@Mapper(componentModel = "spring")
public abstract class IAuthMapper {
    public TokensDTO toTokensDTO(Map<String, String> src) {
        TokensDTO tokensDTO = new TokensDTO();
        try {
            tokensDTO.setAccess_token(src.get("access_token"));
            tokensDTO.setRefresh_token(src.get("refresh_token"));
            tokensDTO.setEmail(src.get("email"));
            tokensDTO.setRole(src.get("role"));
        }
        catch (NullPointerException e) {
            throw new TokenValidationException();
        }
        return tokensDTO;
    }

    public abstract AuthData toAuthData(AuthRequestDTO requestDTO);
}
