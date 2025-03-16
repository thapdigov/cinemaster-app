package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.response.ShowTimeDto;
import az.turing.cinemamasterapp.service.ShowTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RequestMapping("/v1/showtimes")
@RestController
public class ShowTimeController {

    private final ShowTimeService service;

    @GetMapping("/all")
    public ResponseEntity<List<ShowTimeDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/showtime/{id}")
    public ResponseEntity<ShowTimeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findShowById(id));
    }
}
