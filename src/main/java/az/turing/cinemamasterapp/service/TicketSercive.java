package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.Movie;
import az.turing.cinemamasterapp.domain.entity.SeatEntity;
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
import az.turing.cinemamasterapp.model.dto.request.UpdateTicketRequest;
import az.turing.cinemamasterapp.model.dto.response.TicketDto;
import az.turing.cinemamasterapp.model.enums.Status;
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

    public List<TicketDto> findTicketByPrice(Integer price) {
        return repository.findAll().stream()
                .filter(ticket -> ticket.getPrice() <= price)
                .map(ticketMapper::toDto).collect(Collectors.toList());
    }

    public TicketDto create(CreateTicketRequest request) {
        if (repository.existsByTicketNumber(request.getTicketNumber())) {
            throw new AlreadyExistsException("Ticket has already exists with " + request.getTicketNumber());
        }
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + request.getMovieId()));

        UserEntity user = userRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + request.getMovieId()));

        SeatEntity seat = seatRepository.findById(request.getSeatId())
                .orElseThrow(() -> new NotFoundException("Seat not found with id: " + request.getMovieId()));

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

    public TicketDto update(Long id, UpdateTicketRequest updateRequest) {

        TicketEntity ticket = getTicketById(id);

        Movie movie = movieRepository.findById(updateRequest.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found with id:  " + updateRequest.getMovieId()));

        UserEntity user = userRepository.findById(updateRequest.getMovieId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + updateRequest.getMovieId()));

        SeatEntity seat = seatRepository.findById(updateRequest.getSeatId())
                .orElseThrow(() -> new NotFoundException("Seat not found with id: " + updateRequest.getMovieId()));

        ticket.setTicketNumber(updateRequest.getTicketNumber());
        ticket.setPrice(updateRequest.getPrice());
        ticket.setPurchaseDate(updateRequest.getPurchaseDate());
        ticket.setTicketStatus(updateRequest.getTicketStatus());
        ticket.setPaymentMethod(updateRequest.getPaymentMethod());
        ticket.setUser(user);
        ticket.setMovie(movie);
        ticket.setSeat(seat);
        TicketEntity updatedTicket = repository.save(ticket);

        return ticketMapper.toDto(updatedTicket);
    }

    public void delete(Long id) {
        TicketEntity ticket = getTicketById(id);
        ticket.setStatus(Status.DELETE);
    }

    public TicketEntity getTicketById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket not found with id" + id));

    }
}
