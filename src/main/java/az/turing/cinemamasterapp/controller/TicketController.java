package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.request.CreateTicketRequest;
import az.turing.cinemamasterapp.model.dto.response.TicketDto;
import az.turing.cinemamasterapp.service.TicketSercive;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/ticket")
@RequiredArgsConstructor
@Valid
public class TicketController {
    private final TicketSercive ticketSercive;

    @GetMapping("/all")
    public ResponseEntity<List<TicketDto>> getAll() {
        return ResponseEntity.ok(ticketSercive.findAll());
    }

    @PostMapping()
    public ResponseEntity<TicketDto> create(@Validated @RequestBody CreateTicketRequest request) {
        return ResponseEntity.ok(ticketSercive.create(request));
    }
}
