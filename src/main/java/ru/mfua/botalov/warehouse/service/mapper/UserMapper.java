package ru.mfua.botalov.warehouse.service.mapper;

import org.mapstruct.Mapper;
import ru.mfua.botalov.warehouse.entity.UserEntity;
import ru.mfua.botalov.warehouse.model.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDTO(UserEntity userEntity);

    UserEntity fromDTO(UserDto userDto);
}
