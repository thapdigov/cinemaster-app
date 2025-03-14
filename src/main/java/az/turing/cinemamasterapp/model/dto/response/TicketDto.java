package az.turing.cinemamasterapp.model.dto.response;

import az.turing.cinemamasterapp.domain.entity.Movie;
import az.turing.cinemamasterapp.domain.entity.SeatEntity;
import az.turing.cinemamasterapp.domain.entity.UserEntity;
import az.turing.cinemamasterapp.model.enums.PaymentMethod;
import az.turing.cinemamasterapp.model.enums.TicketStatus;
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

    private LocalDateTime purchaseDate;

    private TicketStatus ticketStatus;

    private PaymentMethod paymentMethod;

    private UserEntity user;

    private Movie movie;

    private SeatEntity seat;
}
