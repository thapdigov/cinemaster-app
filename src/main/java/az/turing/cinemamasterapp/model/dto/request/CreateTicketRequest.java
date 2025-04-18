package az.turing.cinemamasterapp.model.dto.request;

import az.turing.cinemamasterapp.model.enums.PaymentMethod;
import az.turing.cinemamasterapp.model.enums.Status;
import az.turing.cinemamasterapp.model.enums.TicketStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
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
@SuperBuilder
@Data
public class CreateTicketRequest {

    @NotBlank
    private String ticketNumber;

    @NotNull
    private Double price;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime purchaseDate;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @NotNull
    @Min(1)
    private Long userId;

    @NotNull
    @Min(1)
    private Long showTimeId;

    @NotNull
    @Min(1)
    private Long seatId;
}
