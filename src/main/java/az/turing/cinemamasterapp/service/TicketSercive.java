package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.SeatEntity;
import az.turing.cinemamasterapp.domain.entity.ShowTimeEntity;
import az.turing.cinemamasterapp.domain.entity.TicketEntity;
import az.turing.cinemamasterapp.domain.entity.UserEntity;
import az.turing.cinemamasterapp.domain.repository.MovieRepository;
import az.turing.cinemamasterapp.domain.repository.SeatRepository;
import az.turing.cinemamasterapp.domain.repository.ShowTimeRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketSercive {

    private final TicketRepository repository;
    private final TicketMapper ticketMapper;
    private final ShowTimeRepository showTimeRepository;
    private final UserEntityRepository userRepository;

    private final MovieRepository movieRepository;
    private final SeatRepository seatRepository;

    public Page<TicketDto> findAll(Pageable pageable) {
        Page<TicketEntity> entityPage = repository.findAll(pageable);
        return entityPage.map(ticketMapper::toDto);
    }

    public Page<TicketDto> findAllTicketByMovieId(Long id, Pageable pageable) {

        if (movieRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Movie not found with id: " + id);
        }
        Page<TicketEntity> entityPage = repository.allTicketByMovieId(id, pageable);
        return entityPage.map(ticketMapper::toDto);
    }

    public Page<TicketDto> findAllTicketByMovieName(String name, Pageable pageable) {
        if (movieRepository.findByName(name).isEmpty()) {
            throw new NotFoundException("Movie not found with name: " + name);
        }
        Page<TicketEntity> entityPage = repository.allTicketByMovieName(name, pageable);
        return entityPage.map(ticketMapper::toDto);
    }

    public Page<TicketDto> findTicketByPrice(Integer price, Pageable pageable) {
        return repository.findByPriceLessThanEqual(price, pageable).map(ticketMapper::toDto);
    }

    public TicketDto create(CreateTicketRequest request) {
        if (repository.existsByTicketNumber(request.getTicketNumber())) {
            throw new AlreadyExistsException("Ticket has already exists with " + request.getTicketNumber());
        }
        ShowTimeEntity showTime = showTimeRepository.findById(request.getShowTimeId())
                .orElseThrow(() -> new NotFoundException("Show Time not found with id: " + request.getShowTimeId()));

        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + request.getUserId()));

        SeatEntity seat = seatRepository.findById(request.getSeatId())
                .orElseThrow(() -> new NotFoundException("Seat not found with id: " + request.getSeatId()));

        TicketEntity ticket = new TicketEntity();

        ticket.setTicketNumber(request.getTicketNumber());
        ticket.setPrice(request.getPrice());
        ticket.setPurchaseDate(request.getPurchaseDate());
        ticket.setTicketStatus(request.getTicketStatus());
        ticket.setPaymentMethod(request.getPaymentMethod());
        ticket.setUser(user);
        ticket.setShowTime(showTime);
        ticket.setSeat(seat);
        TicketEntity savedTicket = repository.save(ticket);

        return ticketMapper.toDto(savedTicket);
    }

    public TicketDto update(Long id, UpdateTicketRequest updateRequest) {

        TicketEntity ticket = getTicketById(id);

        ShowTimeEntity showTime = showTimeRepository.findById(updateRequest.getShowTimeId())
                .orElseThrow(() -> new NotFoundException("Show Time not found with id: " + updateRequest.getShowTimeId()));

        UserEntity user = userRepository.findById(updateRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + updateRequest.getUserId()));

        SeatEntity seat = seatRepository.findById(updateRequest.getSeatId())
                .orElseThrow(() -> new NotFoundException("Seat not found with id: " + updateRequest.getSeatId()));

        ticket.setTicketNumber(updateRequest.getTicketNumber());
        ticket.setPrice(updateRequest.getPrice());
        ticket.setPurchaseDate(updateRequest.getPurchaseDate());
        ticket.setTicketStatus(updateRequest.getTicketStatus());
        ticket.setPaymentMethod(updateRequest.getPaymentMethod());
        ticket.setUser(user);
        ticket.setShowTime(showTime);
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
