package az.turing.cinemamasterapp.model.dto.request;

import az.turing.cinemamasterapp.model.enums.MovieGenre;
import az.turing.cinemamasterapp.model.enums.MovieLanguage;
import az.turing.cinemamasterapp.model.enums.MovieStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class CreateMovieRequest {

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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDate releaseDate;

    @NotNull
    private Double rating;

    @Enumerated(EnumType.STRING)
    private MovieLanguage language;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;
}
