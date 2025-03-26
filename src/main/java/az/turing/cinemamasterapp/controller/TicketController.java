package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.request.CreateTicketRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateTicketRequest;
import az.turing.cinemamasterapp.model.dto.response.TicketDto;
import az.turing.cinemamasterapp.service.TicketSercive;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import java.util.List;

@RestController
@RequestMapping("v1/tickets")
@RequiredArgsConstructor
@Valid
public class TicketController {
    private final TicketSercive ticketSercive;

    @GetMapping("/all")
    public ResponseEntity<List<TicketDto>> getAll() {
        return ResponseEntity.ok(ticketSercive.findAll());
    }

    @GetMapping("/byMovie")
    public ResponseEntity<Page<TicketDto>> allTicketByMovieId(
            @RequestParam Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());

        return ResponseEntity.ok(ticketSercive.findAllTicketByMovieId(id, pageable));
    }

    @GetMapping("/byMovieName/{movieName}")
    public ResponseEntity<List<TicketDto>> allTicketByMovieName(@PathVariable String movieName) {
        return ResponseEntity.ok(ticketSercive.findAllTicketByMovieName(movieName));
    }

    @GetMapping("/ticket/{price}")
    public ResponseEntity<List<TicketDto>> getByPrice(@PathVariable Integer price) {
        return ResponseEntity.ok(ticketSercive.findTicketByPrice(price));
    }


    @PostMapping()
    public ResponseEntity<TicketDto> create(@Validated @RequestBody CreateTicketRequest request) {
        return ResponseEntity.ok(ticketSercive.create(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<TicketDto> update(@PathVariable Long id, @Validated @RequestBody UpdateTicketRequest request) {
        return ResponseEntity.ok(ticketSercive.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        ticketSercive.delete(id);
        return ResponseEntity.noContent().build();
    }
}
