package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.request.CreateHallRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateHallRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @PostMapping
    public ResponseEntity<HallDto> create(@RequestBody CreateHallRequest request) {
        return ResponseEntity.ok(hallService.createHall(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HallDto> update(@PathVariable @NotNull @Min(1) Long id,
                                          @RequestBody UpdateHallRequest request) {
        return ResponseEntity.ok(hallService.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Min(1) Long id) {
        hallService.deleteHall(id);
        return ResponseEntity.noContent().build();
    }
}
