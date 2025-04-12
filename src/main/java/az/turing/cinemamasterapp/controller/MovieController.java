package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.request.CreateMovieRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateMovieRequest;
import az.turing.cinemamasterapp.model.dto.response.MovieDto;
import az.turing.cinemamasterapp.model.enums.MovieGenre;
import az.turing.cinemamasterapp.model.enums.MovieLanguage;
import az.turing.cinemamasterapp.service.MovieService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("v1/movies")
@RequiredArgsConstructor
@Validated
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<Page<MovieDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort) {
        return ResponseEntity.ok(movieService.findAll(page, size, sort));
    }

    @GetMapping("/language")
    public ResponseEntity<Page<MovieDto>> getAllByLanguage(
            @RequestParam MovieLanguage allByLanguage,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort) {
        return ResponseEntity.ok(movieService.getMovieByLanguage(allByLanguage, page, size, sort));
    }

    @GetMapping("/last24h")
    public ResponseEntity<Page<MovieDto>> getByLast24Hours(
            @Parameter(description = "Format: dd/MM/yyyy HH:mm:ss")
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime now,
            @Parameter(description = "Format: dd/MM/yyyy HH:mm:ss")
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime nextDay,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort) {
        return ResponseEntity.ok(movieService.getMovieLast24Hours(now, nextDay, page, size, sort));
    }

    @GetMapping("/movieId/{id}")
    public ResponseEntity<MovieDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findMovieById(id));
    }

    @GetMapping("/moviename")
    public ResponseEntity<Page<MovieDto>> getByName(
            @RequestParam String movieName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sort
    ) {
        return ResponseEntity.ok(movieService.findMovieByName(movieName, page, size, sort));
    }

    @GetMapping("/moviedirector")
    public ResponseEntity<Page<MovieDto>> getByDirector(
            @RequestParam String movieDirector,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sort) {
        return ResponseEntity.ok(movieService.findMovieByDirector(movieDirector,page,size,sort));
    }

    @GetMapping("/moviegenre")
    public ResponseEntity<Page<MovieDto>> getByGenre(
            @RequestParam MovieGenre movieGenre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sort) {
        return ResponseEntity.ok(movieService.findMovieByGenre(movieGenre, page, size, sort));
    }


    @PostMapping
    public ResponseEntity<MovieDto> create(@Valid @RequestBody CreateMovieRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body((movieService.createMovie(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable @Min(1) Long id, @Valid @RequestBody UpdateMovieRequest request) {
        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }
}
