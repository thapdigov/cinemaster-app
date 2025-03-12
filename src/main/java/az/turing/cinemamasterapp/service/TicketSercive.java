package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.Movie;
import az.turing.cinemamasterapp.domain.entity.Seat;
import az.turing.cinemamasterapp.domain.entity.TicketEntity;
import az.turing.cinemamasterapp.domain.entity.UserEntity;
import az.turing.cinemamasterapp.domain.repository.MovieRepository;
import az.turing.cinemamasterapp.domain.repository.SeatRepository;
import az.turing.cinemamasterapp.domain.repository.TicketRepository;
import az.turing.cinemamasterapp.domain.repository.UserEntityRepository;
import az.turing.cinemamasterapp.exception.AlreadyExistsException;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.mapper.TicketMapper;
import az.turing.cinemamasterapp.model.dto.request.CreateTicketRequest;
import az.turing.cinemamasterapp.model.dto.response.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketSercive {

    private final TicketRepository repository;
    private final TicketMapper ticketMapper;
    private final MovieRepository movieRepository;
    private final UserEntityRepository userRepository;
    private final SeatRepository seatRepository;

    public List<TicketDto> findAll() {
        return repository.findAll().stream().map(ticketMapper::toDto).collect(Collectors.toList());
    }

    public TicketDto create(CreateTicketRequest request) {
        if (repository.existsByTicketNumber(request.getTicketNumber())) {
            throw new AlreadyExistsException("Ticket has already exists with " + request.getTicketNumber());
        }
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + request.getMovieId()));

        UserEntity user = userRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + request.getMovieId()));

        Seat seat = seatRepository.findById(request.getSeatId())
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + request.getMovieId()));

        TicketEntity ticket = new TicketEntity();
        ticket.setTicketNumber(request.getTicketNumber());
        ticket.setPrice(request.getPrice());
        ticket.setPurchaseDate(request.getPurchaseDate());
        ticket.setTicketStatus(request.getTicketStatus());
        ticket.setPaymentMethod(request.getPaymentMethod());
        ticket.setUser(user);
        ticket.setMovie(movie);
        ticket.setSeat(seat);
        TicketEntity savedTicket = repository.save(ticket);

        return ticketMapper.toDto(savedTicket);
    }
}
