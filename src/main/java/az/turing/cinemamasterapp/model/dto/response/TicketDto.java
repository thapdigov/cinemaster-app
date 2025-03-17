package az.turing.cinemamasterapp.model.dto.response;

import az.turing.cinemamasterapp.domain.entity.MovieEntity;
import az.turing.cinemamasterapp.domain.entity.SeatEntity;
import az.turing.cinemamasterapp.domain.entity.UserEntity;
import az.turing.cinemamasterapp.model.enums.PaymentMethod;
import az.turing.cinemamasterapp.model.enums.TicketStatus;
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
public class TicketDto {

    private String ticketNumber;

    private Double price;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime purchaseDate;

    private TicketStatus ticketStatus;

    private PaymentMethod paymentMethod;

    private UserEntity user;

    private MovieEntity movie;

    private SeatEntity seat;
}
