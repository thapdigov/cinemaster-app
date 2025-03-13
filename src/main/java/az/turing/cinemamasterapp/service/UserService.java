package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.UserEntity;
import az.turing.cinemamasterapp.domain.repository.UserEntityRepository;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.mapper.UserMapper;
import az.turing.cinemamasterapp.model.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository entityRepository;
    private final UserMapper userMapper;

    public List<UserDto> findAllUser() {
        return entityRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserDto findUserById(Long id) {
        UserEntity user = entityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id:" + id));
        return userMapper.toDto(user);
    }
}
