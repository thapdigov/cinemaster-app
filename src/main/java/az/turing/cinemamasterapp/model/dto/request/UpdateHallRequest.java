package az.turing.cinemamasterapp.model.dto.request;

import az.turing.cinemamasterapp.model.enums.HallStatus;
import az.turing.cinemamasterapp.model.enums.HallType;
import az.turing.cinemamasterapp.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UpdateHallRequest {
    @NotBlank
    private String name;

    @NotNull
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private HallType type;

    @Enumerated(EnumType.STRING)
    private HallStatus hallStatus;

    @Enumerated(EnumType.STRING)
    private Status status;
}
