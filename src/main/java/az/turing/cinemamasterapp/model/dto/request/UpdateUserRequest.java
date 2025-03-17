package az.turing.cinemamasterapp.model.dto.request;

import az.turing.cinemamasterapp.model.enums.Status;
import az.turing.cinemamasterapp.model.enums.UserCountry;
import az.turing.cinemamasterapp.model.enums.UserGender;
import az.turing.cinemamasterapp.model.enums.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UpdateUserRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String phoneNumber;

    @DateTimeFormat(fallbackPatterns = "dd/MM/yyyy, dd-MM-yyyy")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    private UserCountry country;

    @Enumerated(EnumType.STRING)
    private Status status;
}
