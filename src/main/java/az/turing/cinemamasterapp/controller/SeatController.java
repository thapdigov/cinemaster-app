package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.request.CreateSeatRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateSeatRequest;
import az.turing.cinemamasterapp.model.dto.response.SeatDto;
import az.turing.cinemamasterapp.service.SeatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/seats")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("all")
    public ResponseEntity<Page<SeatDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "seatNumber") String sort) {
        return ResponseEntity.ok(seatService.findAll(page, size, sort));
    }

    @GetMapping("/seat/{id}")
    public ResponseEntity<SeatDto> getById(@PathVariable @NotNull @Min(1) Long id) {
        return ResponseEntity.ok(seatService.findSeatById(id));
    }

    @PostMapping
    public ResponseEntity<SeatDto> create(@RequestBody CreateSeatRequest request) {
        return ResponseEntity.ok(seatService.createSeat(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatDto> update(@PathVariable @NotNull @Min(1) Long id,
                                          @RequestBody UpdateSeatRequest request) {
        return ResponseEntity.ok(seatService.updateSeatById(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Min(1) Long id) {
        seatService.deleteSeatById(id);
        return ResponseEntity.noContent().build();
    }
}
