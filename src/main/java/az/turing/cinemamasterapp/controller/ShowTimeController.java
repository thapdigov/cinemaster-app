package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.request.CreateShowTimeRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateShowTimeRequest;
import az.turing.cinemamasterapp.model.dto.response.ShowTimeDto;
import az.turing.cinemamasterapp.service.ShowTimeService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

@RequiredArgsConstructor
@Validated
@RequestMapping("/v1/showtimes")
@RestController
public class ShowTimeController {

    private final ShowTimeService service;

    @GetMapping("/all")
    public ResponseEntity<Page<ShowTimeDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "startTime") String sort) {
        return ResponseEntity.ok(service.findAll(page, size, sort));
    }

    @GetMapping("/showtime/{id}")
    public ResponseEntity<ShowTimeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findShowById(id));
    }

    @PostMapping
    public ResponseEntity<ShowTimeDto> create(@RequestBody CreateShowTimeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createShowTime(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowTimeDto> update(@PathVariable @Min(1) Long id, @RequestBody UpdateShowTimeRequest request) {
        return ResponseEntity.ok(service.updateShowTime(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
