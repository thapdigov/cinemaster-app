package az.turing.cinemamasterapp.model.dto.request;

import az.turing.cinemamasterapp.model.enums.MovieGenre;
import az.turing.cinemamasterapp.model.enums.MovieLanguage;
import az.turing.cinemamasterapp.model.enums.MovieStatus;
import az.turing.cinemamasterapp.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateMovieRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    @NotBlank
    private String director;
    @NotNull
    private Integer duration;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime releaseDate;

    @NotNull
    private Double rating;

    @Enumerated(EnumType.STRING)
    private MovieLanguage language;

    @Enumerated(EnumType.STRING)
    private MovieStatus movieStatus;

    @Enumerated(EnumType.STRING)
    private Status status;
}
