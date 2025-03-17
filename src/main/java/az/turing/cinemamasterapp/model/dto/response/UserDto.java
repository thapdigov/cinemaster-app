package az.turing.cinemamasterapp.model.dto.response;

import az.turing.cinemamasterapp.model.enums.Status;
import az.turing.cinemamasterapp.model.enums.UserCountry;
import az.turing.cinemamasterapp.model.enums.UserGender;
import az.turing.cinemamasterapp.model.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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


    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate birthday;

    private UserGender gender;

    private UserStatus userStatus;

    private Status status;

    private UserCountry country;
}
