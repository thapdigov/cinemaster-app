package az.turing.cinemamasterapp.model.dto.request;

import az.turing.cinemamasterapp.model.enums.PaymentMethod;
import az.turing.cinemamasterapp.model.enums.Status;
import az.turing.cinemamasterapp.model.enums.TicketStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class UpdateTicketRequest {

    @NotBlank
    private String ticketNumber;

    @NotNull
    private Integer price;

    @DateTimeFormat(fallbackPatterns = "dd/MM/yyyy HH:mm:ss, dd-MM-yyyy HH:mm:ss")
    private LocalDateTime purchaseDate;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @NotNull
    private Long userId;

    @NotNull
    private Long movieId;

    @NotNull
    private Long seatId;
}
