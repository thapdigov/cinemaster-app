package az.turing.cinemamasterapp.model.dto.response;

import az.turing.cinemamasterapp.model.enums.MovieGenre;
import az.turing.cinemamasterapp.model.enums.MovieLanguage;
import az.turing.cinemamasterapp.model.enums.MovieStatus;
import az.turing.cinemamasterapp.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MovieDto {

    private String name;
    private String description;
    private MovieGenre genre;
    private String director;
    private Integer duration;
    private LocalDateTime releaseDate;
    private Double rating;
    private MovieLanguage language;
    private Status status;
    private MovieStatus movieStatus;
}
