package az.turing.cinemamasterapp.mapper;

import az.turing.cinemamasterapp.domain.entity.Movie;
import az.turing.cinemamasterapp.model.dto.request.CreateMovieRequest;
import az.turing.cinemamasterapp.model.dto.response.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements EntityMapper<Movie, MovieDto> {

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

    @Override
    public Movie toEnt(MovieDto movieDto) {
        return Movie.builder()
                .name(movieDto.getName())
                .description(movieDto.getDescription())
                .genre(movieDto.getGenre())
                .director(movieDto.getDirector())
                .duration(movieDto.getDuration())
                .releaseDate(movieDto.getReleaseDate())
                .rating(movieDto.getRating())
                .language(movieDto.getLanguage())
                .status(movieDto.getStatus())
                .movieStatus(movieDto.getMovieStatus())
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
                .movieStatus(movie.getMovieStatus())
                .build();
    }
}
