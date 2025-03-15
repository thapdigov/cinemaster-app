package az.turing.cinemamasterapp.model.dto.request;

import az.turing.cinemamasterapp.model.enums.SeatStatus;
import az.turing.cinemamasterapp.model.enums.SeatType;
import az.turing.cinemamasterapp.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class UpdateSeatRequest {

    @NotBlank
    private String row;

    @Min(1)
    @NotNull
    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType type;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Min(1)
    @NotNull
    private Long hallId;
}
