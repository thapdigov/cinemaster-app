package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.UserEntity;
import az.turing.cinemamasterapp.domain.repository.UserEntityRepository;
import az.turing.cinemamasterapp.exception.InvalidPasswordConfirmationException;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.mapper.UserMapper;
import az.turing.cinemamasterapp.model.dto.request.CreateUserRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateUserRequest;
import az.turing.cinemamasterapp.model.dto.response.UserDto;
import az.turing.cinemamasterapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository entityRepository;
    private final UserMapper userMapper;

    public List<UserDto> findAll() {
        return entityRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserDto findUserById(Long id) {
        UserEntity user = findById(id);
        return userMapper.toDto(user);
    }

    public UserDto createUser(CreateUserRequest request) {

        if (!(request.getPassword().equals(request.getConfirmPassword()))) {
            throw new InvalidPasswordConfirmationException("Password and confirmPassword is not the same!");
        }

        UserEntity user = new UserEntity();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setBirthday(request.getBirthday());
        user.setGender(request.getGender());
        user.setStatus(request.getStatus());
        user.setUserStatus(request.getUserStatus());
        user.setCountry(request.getCountry());

        UserEntity savedUser = entityRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    public UserDto updateUserById(Long id, UpdateUserRequest request) {

        UserEntity user = findById(id);

        if (!(request.getPassword().equals(request.getConfirmPassword()))) {
            throw new InvalidPasswordConfirmationException("Password and confirmPassword is not the same!");
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setBirthday(request.getBirthday());
        user.setGender(request.getGender());
        user.setStatus(request.getStatus());
        user.setUserStatus(request.getUserStatus());
        user.setCountry(request.getCountry());

        UserEntity updatedUser = entityRepository.save(user);

        return userMapper.toDto(updatedUser);
    }

    public UserEntity findById(Long id) {
        return entityRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id:" + id));
    }

    public void deleteUserById(Long id) {
        //soft delete
        UserEntity user = findById(id);
        user.setStatus(Status.DELETE);

        //hard delete
        //    entityRepository.deleteById(id);
    }
}