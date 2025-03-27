package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.CinemaHallEntity;
import az.turing.cinemamasterapp.domain.entity.SeatEntity;
import az.turing.cinemamasterapp.domain.repository.CinemaHallRepository;
import az.turing.cinemamasterapp.domain.repository.SeatRepository;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.mapper.SeatMapper;
import az.turing.cinemamasterapp.model.dto.request.CreateSeatRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateSeatRequest;
import az.turing.cinemamasterapp.model.dto.response.SeatDto;
import az.turing.cinemamasterapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository repository;
    private final CinemaHallRepository hallRepository;
    private final SeatMapper seatMapper;

    public Page<SeatDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(seatMapper::toDto);
    }


    public SeatDto findSeatById(Long id) {
        SeatEntity seatEntity = findById(id);
        return seatMapper.toDto(seatEntity);
    }


    public SeatDto createSeat(CreateSeatRequest request) {

        SeatEntity seat = new SeatEntity();

        CinemaHallEntity cinemaHall = hallRepository.findById(request.getHallId())
                .orElseThrow(() -> new NotFoundException("CinemaHall not found with id: " + request.getHallId()));

        seat.setRow(request.getRow());
        seat.setSeatNumber(request.getSeatNumber());
        seat.setType(request.getType());
        seat.setSeatStatus(request.getSeatStatus());
        seat.setStatus(request.getStatus());
        seat.setSeatNumber(request.getSeatNumber());
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

    public SeatEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Seat not found with id:" + id));
    }

    public void deleteSeatById(Long id) {
        //soft delete
        SeatEntity seatEntity = findById(id);
        seatEntity.setStatus(Status.DELETE);

        //hard delete
        //    entityRepository.deleteById(id);
    }
}