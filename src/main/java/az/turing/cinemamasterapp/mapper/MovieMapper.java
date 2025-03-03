package az.turing.cinemamasterapp.mapper;

import az.turing.cinemamasterapp.domain.entity.Movie;
import az.turing.cinemamasterapp.model.dto.request.CreateMovieRequest;
import az.turing.cinemamasterapp.model.dto.response.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie toEnt(CreateMovieRequest request) {
        return Movie.builder()
                .name(request.getName())
                .description(request.getDescription())
                .genre(request.getGenre())
                .director(request.getDirector())
                .duration(request.getDuration())
                .releaseDate(request.getReleaseDate())
                .rating(request.getRating())
                .language(request.getLanguage())
                .status(request.getStatus())
                .build();
    }

    public MovieDto toDto(Movie movie) {
        return MovieDto.builder()
                .name(movie.getName())
                .description(movie.getDescription())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .language(movie.getLanguage())
                .status(movie.getStatus())
                .build();
    }
}
