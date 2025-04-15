package az.turing.cinemamasterapp.mapper;

import az.turing.cinemamasterapp.domain.entity.UserEntity;
import az.turing.cinemamasterapp.model.dto.request.CreateUserRequest;
import az.turing.cinemamasterapp.model.dto.response.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityMapper<UserEntity, UserDto> {
    @Override
    public UserEntity toEnt(UserDto userDto) {
        return UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .birthday(userDto.getBirthday())
                .gender(userDto.getGender())
                .status(userDto.getStatus())
                .userStatus(userDto.getUserStatus())
                .country(userDto.getCountry())
                .build();
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phoneNumber(userEntity.getPhoneNumber())
                .birthday(userEntity.getBirthday())
                .gender(userEntity.getGender())
                .userStatus(userEntity.getUserStatus())
                .status(userEntity.getStatus())
                .country(userEntity.getCountry())
                .updatedAt(userEntity.getUpdatedAt())
                .createdAt(userEntity.getCreatedAt())
                .build();
    }
    public UserEntity toEnt(CreateUserRequest request) {
        return UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .birthday(request.getBirthday())
                .gender(request.getGender())
                .status(request.getStatus())
                .userStatus(request.getUserStatus())
                .country(request.getCountry())
                .build();
    }
}
