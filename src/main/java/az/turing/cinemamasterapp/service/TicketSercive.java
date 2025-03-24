package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.SeatEntity;
import az.turing.cinemamasterapp.domain.entity.ShowTimeEntity;
import az.turing.cinemamasterapp.domain.entity.TicketEntity;
import az.turing.cinemamasterapp.domain.entity.UserEntity;
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
