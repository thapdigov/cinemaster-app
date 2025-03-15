package az.turing.cinemamasterapp.model.dto.response;

import az.turing.cinemamasterapp.domain.entity.CinemaHallEntity;
import az.turing.cinemamasterapp.model.enums.SeatStatus;
import az.turing.cinemamasterapp.model.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {

    private String row;

    private Integer seatNumber;

    private SeatType type;

    private SeatStatus seatStatus;

    private CinemaHallEntity cinemaHall;
}
