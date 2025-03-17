package az.turing.cinemamasterapp.model.dto.request;

import az.turing.cinemamasterapp.model.enums.ShowTimeStatus;
import az.turing.cinemamasterapp.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UpdateShowTimeRequest {

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private ShowTimeStatus showTimeStatus;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Min(1)
    private Long movieId;

    @Min(1)
    private Long hallId;
}
