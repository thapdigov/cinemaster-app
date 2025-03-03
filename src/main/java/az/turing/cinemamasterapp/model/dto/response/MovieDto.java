package az.turing.cinemamasterapp.model.dto.response;

import az.turing.cinemamasterapp.model.enums.MovieGenre;
import az.turing.cinemamasterapp.model.enums.MovieLanguage;
import az.turing.cinemamasterapp.model.enums.MovieStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {

    private String name;
    private String description;
    private MovieGenre genre;
    private String director;
    private Integer duration;
    private LocalDate releaseDate;
    private Double rating;
    private MovieLanguage language;
    private MovieStatus status;
}
