package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.request.CreateSeatRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateSeatRequest;
import az.turing.cinemamasterapp.model.dto.response.SeatCodeDto;
import az.turing.cinemamasterapp.model.dto.response.SeatDto;
import az.turing.cinemamasterapp.service.SeatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/seats")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/codes")
    public ResponseEntity<Page<SeatDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "seatNumber") String sort) {
        return ResponseEntity.ok(seatService.findAll(page, size, sort));
    }

    @GetMapping("/seat-codes")
    public ResponseEntity<Page<SeatCodeDto>> getAllRowAndSeatNumber(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "seatNumber") String sort) {
        return ResponseEntity.ok(seatService.findAllRowSeat(page, size, sort));
    }

    @GetMapping("/seat/{id}")
    public ResponseEntity<SeatDto> getById(@PathVariable @NotNull @Min(1) Long id) {
        return ResponseEntity.ok(seatService.findSeatById(id));
    }

    @PostMapping
    public ResponseEntity<SeatDto> create(@RequestBody @Valid CreateSeatRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(seatService.createSeat(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatDto> update(@PathVariable @NotNull @Min(1) Long id,
                                          @RequestBody @Valid UpdateSeatRequest request) {
        return ResponseEntity.ok(seatService.updateSeatById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Min(1) Long id) {
        seatService.deleteSeatById(id);
        return ResponseEntity.noContent().build();
    }
}
