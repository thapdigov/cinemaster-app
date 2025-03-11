package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.Movie;
import az.turing.cinemamasterapp.domain.repository.MovieRepository;
import az.turing.cinemamasterapp.exception.AlreadyExistsException;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.exception.TimeException;
import az.turing.cinemamasterapp.mapper.MovieMapper;
import az.turing.cinemamasterapp.model.dto.request.CreateMovieRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateMovieRequest;
import az.turing.cinemamasterapp.model.dto.response.MovieDto;
import az.turing.cinemamasterapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<MovieDto> findAll() {
        return movieRepository.findAll().stream().map(movieMapper::toDto).collect(Collectors.toList());
    }

    public MovieDto findMovieById(Long id) {
        return movieMapper.toDto(movieRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Movie not found with id " + id)));
    }

    public MovieDto findMovieByName(String movieName) {
        return movieMapper.toDto(movieRepository.findByName(movieName).
                orElseThrow(() -> new NotFoundException("Movie not found with id " + movieName)));
    }

    public List<MovieDto> getMovieLast24Hours(LocalDateTime now, LocalDateTime nextDay) {
        return movieRepository.findByMoviesLast24hours(now, nextDay)
                .stream().map(movieMapper::toDto).collect(Collectors.toList());
    }

    public MovieDto createMovie(CreateMovieRequest request) {

        if (request.getReleaseDate().isBefore(LocalDateTime.now())) {
            throw new TimeException("Movie time is not right!");
        }

        if (movieRepository.existsByNameAndDescriptionAndDirector(
                request.getName(), request.getDescription(), request.getDirector())) {
            throw new AlreadyExistsException("The Movie already is exists!!");
        }

        Movie savedMovie = movieRepository.save(movieMapper.toEnt(request));
        return movieMapper.toDto(savedMovie);
    }


    public void deleteMovieById(long id) {
        Movie deletedMovie = findById(id);
        deletedMovie.setStatus(Status.DELETE);
    }

    public MovieDto updateMovie(Long id, UpdateMovieRequest request) {
        Movie movie = findById(id);
        movie.setName(request.getName());
        movie.setDescription(request.getDescription());
        movie.setGenre(request.getGenre());
        movie.setDirector(request.getDirector());
        movie.setDuration(request.getDuration());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setRating(request.getRating());
        movie.setLanguage(request.getLanguage());
        movie.setMovieStatus(request.getMovieStatus());
        movie.setStatus(request.getStatus());
        Movie updatedMovie = movieRepository.save(movie);
        return movieMapper.toDto(updatedMovie);
    }

    public Movie findById(long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie not found with " + id));
    }
}
