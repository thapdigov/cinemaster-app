package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.Movie;
import az.turing.cinemamasterapp.domain.repository.MovieRepository;
import az.turing.cinemamasterapp.exception.AlreadyExistsException;
import az.turing.cinemamasterapp.mapper.MovieMapper;
import az.turing.cinemamasterapp.model.dto.response.MovieDto;
import az.turing.cinemamasterapp.model.dto.request.CreateMovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public MovieDto create(CreateMovieRequest request) {
        if (movieRepository.existsByNameAndDescriptionAndDirector
                (request.getName(), request.getDescription(), request.getDirector())) {
            throw new AlreadyExistsException("The Movie has already!");
        }
        Movie movie = movieRepository.save(movieMapper.toEnt(request));
        return movieMapper.toDto(movie);
    }

    public void deleteMovieById(long id) {
        movieRepository.deleteById(id);
    }
}
