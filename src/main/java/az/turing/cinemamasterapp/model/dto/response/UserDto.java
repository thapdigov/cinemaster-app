package az.turing.cinemamasterapp.model.dto.response;

import az.turing.cinemamasterapp.model.enums.UserCountry;
import az.turing.cinemamasterapp.model.enums.UserGender;
import az.turing.cinemamasterapp.model.enums.UserRole;
import az.turing.cinemamasterapp.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private LocalDate birthday;

    private UserGender gender;

    private UserRole role;

    private UserStatus userStatus;

    private UserCountry country;
}
