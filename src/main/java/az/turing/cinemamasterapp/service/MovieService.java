package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.Movie;
import az.turing.cinemamasterapp.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
