package az.turing.cinemamasterapp.model.dto.response;

import az.turing.cinemamasterapp.domain.entity.CinemaHallEntity;
import az.turing.cinemamasterapp.domain.entity.MovieEntity;
import az.turing.cinemamasterapp.model.enums.ShowTimeStatus;
import az.turing.cinemamasterapp.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ShowTimeDto {

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startTime;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endTime;

    private ShowTimeStatus showTimeStatus;

    private Status status;

    private MovieEntity movie;

    private CinemaHallEntity cinemaHall;
}
