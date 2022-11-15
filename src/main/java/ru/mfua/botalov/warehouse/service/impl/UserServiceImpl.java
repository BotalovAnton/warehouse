package ru.mfua.botalov.warehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mfua.botalov.warehouse.exception.UserException;
import ru.mfua.botalov.warehouse.model.UserDto;
import ru.mfua.botalov.warehouse.repository.UserRepository;
import ru.mfua.botalov.warehouse.service.UserService;
import ru.mfua.botalov.warehouse.service.mapper.UserMapper;
import ru.mfua.botalov.warehouse.service.utils.Utils;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Utils utils;

    @Override
    public String addUser(UserDto userDto) {
        userDto.setCreated(OffsetDateTime.now());
        var savedId = userRepository.save(userMapper.fromDTO(userDto)).getId();
        return String.valueOf(savedId);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        var oldUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserException("Пользователь не найден"));

        var newUserEntity = userMapper.fromDTO(userDto);

        utils.merge(newUserEntity, oldUserEntity);

        return userMapper.toDTO(userRepository.save(newUserEntity));
    }

    @Override
    public UserDto getUser(Long id) {

        var userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserException("Пользователь не найден"));

        return userMapper.toDTO(userEntity);
    }

    @Override
    public UserDto getUserByLogin(String login) {
        var userEntity = userRepository.findByLogin(login)
                .orElseThrow(() -> new UserException("Пользователь не найден"));

        return userMapper.toDTO(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
