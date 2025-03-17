package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.request.CreateShowTimeRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateShowTimeRequest;
import az.turing.cinemamasterapp.model.dto.response.ShowTimeDto;
import az.turing.cinemamasterapp.service.ShowTimeService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<ShowTimeDto> create(@RequestBody CreateShowTimeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createShowTime(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<ShowTimeDto> update(@PathVariable @Min(1) Long id, @RequestBody UpdateShowTimeRequest request) {
        return ResponseEntity.ok(service.updateShowTime(id, request));
    }

    public ResponseEntity<Void> delete(Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
