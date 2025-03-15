package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.response.HallDto;
import az.turing.cinemamasterapp.service.HallService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/halls")
public class HallController {

    private final HallService hallService;

    @GetMapping("all")
    public ResponseEntity<List<HallDto>> getAll() {
        return ResponseEntity.ok(hallService.findAll());
    }

    @GetMapping("/hall/{id}")
    public ResponseEntity<HallDto> getById(@PathVariable @NotNull @Min(1) Long id) {
        return ResponseEntity.ok(hallService.findHallById(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Min(1) Long id) {
        hallService.deleteHall(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping
//    public ResponseEntity<SeatDto> create(@RequestBody CreateSeatRequest request) {
//        return ResponseEntity.ok(seatService.createSeat(request));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<SeatDto> create(@PathVariable @NotNull @Min(1) Long id,
//                                          @RequestBody UpdateSeatRequest request) {
//        return ResponseEntity.ok(seatService.updateSeatById(id, request));
//    }

}
