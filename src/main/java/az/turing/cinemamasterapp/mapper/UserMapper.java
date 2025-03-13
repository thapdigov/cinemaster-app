package az.turing.cinemamasterapp.mapper;

import az.turing.cinemamasterapp.domain.entity.UserEntity;
import az.turing.cinemamasterapp.model.dto.response.UserDto;

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
                .role(userDto.getRole())
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
                .role(userEntity.getRole())
                .userStatus(userEntity.getUserStatus())
                .country(userEntity.getCountry())
                .build();
    }
}
