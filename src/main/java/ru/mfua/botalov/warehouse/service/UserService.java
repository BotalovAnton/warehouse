package ru.mfua.botalov.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mfua.botalov.warehouse.exception.UserException;
import ru.mfua.botalov.warehouse.model.UserDto;
import ru.mfua.botalov.warehouse.repository.UserRepository;
import ru.mfua.botalov.warehouse.service.mapper.UserMapper;
import ru.mfua.botalov.warehouse.service.utils.Utils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Utils utils;

    public String addUser(UserDto userDto) {
        var savedId = userRepository.save(userMapper.fromDTO(userDto)).getId();
        return String.valueOf(savedId);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        var oldUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserException("Пользователь не найден"));

        var newUserEntity = userMapper.fromDTO(userDto);

        utils.merge(newUserEntity, oldUserEntity);

        return userMapper.toDTO(userRepository.save(newUserEntity));
    }

    public UserDto getUser(Long id) {

        var userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserException("Пользователь не найден"));

        return userMapper.toDTO(userEntity);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
