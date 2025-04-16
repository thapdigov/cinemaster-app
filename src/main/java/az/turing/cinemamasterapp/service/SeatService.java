package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.CinemaHallEntity;
import az.turing.cinemamasterapp.domain.entity.SeatEntity;
import az.turing.cinemamasterapp.domain.repository.CinemaHallRepository;
import az.turing.cinemamasterapp.domain.repository.SeatRepository;
import az.turing.cinemamasterapp.exception.AlreadyExistsException;
import az.turing.cinemamasterapp.exception.InvalidNumberException;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.mapper.SeatMapper;
import az.turing.cinemamasterapp.model.dto.request.CreateSeatRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateSeatRequest;
import az.turing.cinemamasterapp.model.dto.response.SeatCodeDto;
import az.turing.cinemamasterapp.model.dto.response.SeatDto;
import az.turing.cinemamasterapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository repository;
    private final CinemaHallRepository hallRepository;
    private final SeatMapper seatMapper;

    public Page<SeatDto> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<SeatEntity> entityPage = repository.findAll(pageable);
        List<SeatDto> dtoList = entityPage.stream().
                filter(seat -> seat.getStatus() != Status.DELETE).map(seatMapper::toDto).toList();
        return new PageImpl<>(dtoList, pageable, dtoList.size());
    }

    public Page<SeatCodeDto> findAllRowSeat(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return repository.findAll(pageable).map(seatMapper::codeDto);
    }


    public SeatDto createSeat(CreateSeatRequest request) {

        if (repository.existsByRowAndSeatNumber(request.getSeatRow(), request.getSeatNumber())) {
            throw new AlreadyExistsException("This seat is already exists with "
                    + request.getSeatRow() + request.getSeatNumber());
        }

        CinemaHallEntity cinemaHall = hallRepository.findById(request.getHallId())
                .orElseThrow(() -> new NotFoundException("CinemaHall not found with id: " + request.getHallId()));

        SeatEntity seat = new SeatEntity();
        seat.setRow(request.getSeatRow());
        if (request.getSeatNumber() <= cinemaHall.getCapacity()) {
            seat.setSeatNumber(request.getSeatNumber());
        } else {
            throw new InvalidNumberException("Seat number cannot be greater than " + cinemaHall.getCapacity());
        }
        seat.setType(request.getType());
        seat.setSeatStatus(request.getSeatStatus());
        seat.setStatus(request.getStatus());
        seat.setCinemaHall(cinemaHall);
        SeatEntity savedSeat = repository.save(seat);

        return seatMapper.toDto(savedSeat);
    }


    public SeatDto updateSeatById(Long id, UpdateSeatRequest request) {

        CinemaHallEntity cinemaHall = hallRepository.findById(request.getHallId())
                .orElseThrow(() -> new NotFoundException("CinemaHall not found with id: " + request.getHallId()));

        SeatEntity seat = findById(id);
        seat.setRow(request.getRow());
        seat.setSeatNumber(request.getSeatNumber());
        seat.setSeatStatus(request.getSeatStatus());
        seat.setStatus(request.getStatus());
        seat.setType(request.getType());
        seat.setCinemaHall(cinemaHall);
        SeatEntity updatedSeat = repository.save(seat);

        return seatMapper.toDto(updatedSeat);
    }

    public SeatDto findSeatById(Long id) {
        SeatEntity seatEntity = findById(id);
        return seatMapper.toDto(seatEntity);
    }

    public SeatEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Seat not found with id:" + id));
    }

    public void deleteSeatById(Long id) {
        SeatEntity seatEntity = findById(id);
        seatEntity.setStatus(Status.DELETE);
        repository.save(seatEntity);
    }
}