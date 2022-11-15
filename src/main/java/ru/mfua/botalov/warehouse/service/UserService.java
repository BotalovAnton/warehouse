package ru.mfua.botalov.warehouse.service;

import ru.mfua.botalov.warehouse.model.UserDto;
import java.util.List;

public interface UserService {

    String addUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto);

    UserDto getUser(Long id);

    UserDto getUserByLogin(String login);

    List<UserDto> getAllUsers();

    void deleteUser(Long id);
}
