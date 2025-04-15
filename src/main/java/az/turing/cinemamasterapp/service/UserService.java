package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.UserEntity;
import az.turing.cinemamasterapp.domain.repository.UserEntityRepository;
import az.turing.cinemamasterapp.exception.AlreadyExistsException;
import az.turing.cinemamasterapp.exception.InvalidPasswordConfirmationException;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.exception.TimeException;
import az.turing.cinemamasterapp.mapper.UserMapper;
import az.turing.cinemamasterapp.model.dto.request.CreateUserRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateUserRequest;
import az.turing.cinemamasterapp.model.dto.response.UserDto;
import az.turing.cinemamasterapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository entityRepository;
    private final UserMapper userMapper;

    public Page<UserDto> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<UserEntity> entityPage = entityRepository.findAll(pageable);

        List<UserDto> dtoList = entityPage
                .filter(userEntity -> userEntity.getStatus() != Status.DELETE).map(userMapper::toDto).toList();

        return new PageImpl<>(dtoList, pageable, dtoList.size());
    }

    public UserDto findUserById(Long id) {
        UserEntity user = findById(id);
        return userMapper.toDto(user);
    }

    public UserDto createUser(CreateUserRequest request) {

        if (entityRepository.existsByFirstNameAndLastName(request.getFirstName(), request.getLastName())) {
            throw new AlreadyExistsException("User has already exists with : " + request.getFirstName() + " "
                    + request.getLastName());
        }

        if (!(request.getPassword().equals(request.getConfirmPassword()))) {
            throw new InvalidPasswordConfirmationException("Password and confirmPassword is not the same!");
        }
        if (request.getBirthday().isAfter(LocalDate.now())) {
            throw new TimeException("Birthday is not right!");
        }
        UserEntity user = userMapper.toEnt(request);
        UserEntity savedUser = entityRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDto updateUserById(Long id, UpdateUserRequest request) {

        UserEntity user = findById(id);

        if (!(request.getPassword().equals(request.getConfirmPassword()))) {
            throw new InvalidPasswordConfirmationException("Password and confirmPassword is not the same!");
        }

        if (entityRepository.existsByFirstNameAndLastName(request.getFirstName(), request.getLastName())) {
            throw new AlreadyExistsException("User has already exists with : " + request.getFirstName() + " "
                    + request.getLastName());
        }

        if (request.getBirthday().isAfter(LocalDate.now())) {
            throw new TimeException("Birthday is not right!");
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
        UserEntity deletedEntity = findById(id);
        deletedEntity.setStatus(Status.DELETE);
        entityRepository.save(deletedEntity);
    }
}