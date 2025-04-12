package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.MovieEntity;
import az.turing.cinemamasterapp.domain.repository.MovieRepository;
import az.turing.cinemamasterapp.exception.AlreadyExistsException;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.exception.TimeException;
import az.turing.cinemamasterapp.mapper.MovieMapper;
import az.turing.cinemamasterapp.model.dto.request.CreateMovieRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateMovieRequest;
import az.turing.cinemamasterapp.model.dto.response.MovieDto;
import az.turing.cinemamasterapp.model.enums.MovieGenre;
import az.turing.cinemamasterapp.model.enums.MovieLanguage;
import az.turing.cinemamasterapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public Page<MovieDto> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        return movieRepository.findAll(pageable).map(movieMapper::toDto);
    }

    public MovieDto findMovieById(Long id) {
        return movieMapper.toDto(movieRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Movie not found with id " + id)));
    }

    public Page<MovieDto> findMovieByName(String movieName, int page, int size, String sort) {
        Pageable pagable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<MovieEntity> movieEntityPage = movieRepository.findListByName(movieName, pagable);

        if (movieEntityPage.isEmpty()) {
            throw new NotFoundException("Movie not found with name: " + movieName);
        }
        return movieEntityPage.map(movieMapper::toDto);
    }

    public Page<MovieDto> findMovieByDirector(String director, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<MovieEntity> entityPage = movieRepository.findByDirector(director, pageable);
        if (entityPage.isEmpty()) {
            throw new NotFoundException("Movie not found with name: " + director);
        }
        return entityPage.map(movieMapper::toDto);
    }

    public Page<MovieDto> findMovieByGenre(MovieGenre genre, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<MovieEntity> entityPage = movieRepository.findByGenre(genre, pageable);
        if (entityPage.isEmpty()) {
            throw new NotFoundException("Movie not found with name: " + genre);
        }

        return entityPage.map(movieMapper::toDto);
    }

    public Page<MovieDto> getMovieByLanguage(MovieLanguage language, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        return movieRepository.findByLanguage(language, pageable)
                .map(movieMapper::toDto);
    }


    public Page<MovieDto> getMovieLast24Hours(LocalDateTime now, LocalDateTime nextDay,
                                              int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        return movieRepository.findByMoviesLast24hours(now, nextDay, pageable)
                .map(movieMapper::toDto);
    }

    public MovieDto createMovie(CreateMovieRequest request) {

        if (request.getReleaseDate().isBefore(LocalDateTime.now())) {
            throw new TimeException("Movie time is not right!");
        }

        if (movieRepository.existsByNameAndDescriptionAndDirector(
                request.getName(), request.getDescription(), request.getDirector())) {
            throw new AlreadyExistsException("The Movie already is exists!!");
        }

        MovieEntity savedMovie = movieRepository.save(movieMapper.toEnt(request));
        return movieMapper.toDto(savedMovie);
    }


    public void deleteMovieById(long id) {
        MovieEntity deletedMovie = findById(id);
        deletedMovie.setStatus(Status.DELETE);
        movieRepository.save(deletedMovie);
    }

    public MovieDto updateMovie(Long id, UpdateMovieRequest request) {
        MovieEntity movie = findById(id);
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
        MovieEntity updatedMovie = movieRepository.save(movie);
        return movieMapper.toDto(updatedMovie);
    }

    public MovieEntity findById(long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie not found with " + id));
    }
}
