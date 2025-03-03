package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.domain.entity.Movie;
import az.turing.cinemamasterapp.model.dto.request.CreateMovieRequest;
import az.turing.cinemamasterapp.model.dto.response.MovieDto;
import az.turing.cinemamasterapp.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/movies")
@RequiredArgsConstructor
@Valid
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @PostMapping
    public ResponseEntity<MovieDto> create(@Validated CreateMovieRequest request) {
        return ResponseEntity.ok(movieService.create(request));
    }
}
