package az.turing.cinemamasterapp.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "phone_number", nullable = false)
    @NotBlank
    private String phoneNumber;

    @Column(name = "birthday", nullable = false)
    private String birthday;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "first_name", nullable = false)
    private String status;
    @Column(name = "first_name", nullable = false)
    private String role;
    @Column(name = "first_name", nullable = false)
    private String country;
    @Column(name = "first_name", nullable = false)
    private String createdAt;
    @Column(name = "first_name", nullable = false)
    private String ticket;
    @Column(name = "first_name", nullable = false)
    private String updatedAt;
}
