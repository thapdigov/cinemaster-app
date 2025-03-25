package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.request.CreateMovieRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateMovieRequest;
import az.turing.cinemamasterapp.model.dto.response.MovieDto;
import az.turing.cinemamasterapp.model.enums.MovieGenre;
import az.turing.cinemamasterapp.model.enums.MovieLanguage;
import az.turing.cinemamasterapp.service.MovieService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.List;

@RestController
@RequestMapping("v1/movies")
@RequiredArgsConstructor
@Valid
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/language/{allByLanguage}")
    public ResponseEntity<List<MovieDto>> getAllByLanguage(@RequestParam MovieLanguage allByLanguage) {
        return ResponseEntity.ok(movieService.getMovieByLanguage(allByLanguage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findMovieById(id));
    }

    @GetMapping("/moviename/{movieName}")
    public ResponseEntity<MovieDto> getByName(@PathVariable String movieName) {
        return ResponseEntity.ok(movieService.findMovieByName(movieName));
    }

    @GetMapping("/moviedirector/{movieDirector}")
    public ResponseEntity<MovieDto> getByDirector(@PathVariable String movieDirector) {
        return ResponseEntity.ok(movieService.findMovieByDirector(movieDirector));
    }

    @GetMapping("/moviegenre/{movieGenre}")
    public ResponseEntity<MovieDto> getByGenre(@PathVariable MovieGenre movieGenre) {
        return ResponseEntity.ok(movieService.findMovieByGenre(movieGenre));
    }

    @GetMapping("/last24h/{now}/{nextDay}")
    public ResponseEntity<List<MovieDto>> getByLastHours(
            @PathVariable @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime now,
            @PathVariable @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime nextDay) {
        return ResponseEntity.ok(movieService.getMovieLast24Hours(now, nextDay));
    }


    @PostMapping
    public ResponseEntity<MovieDto> create(@Valid @RequestBody CreateMovieRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body((movieService.createMovie(request)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<MovieDto> update(@PathVariable @Min(1) Long id, @RequestBody UpdateMovieRequest request) {
        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }
}
