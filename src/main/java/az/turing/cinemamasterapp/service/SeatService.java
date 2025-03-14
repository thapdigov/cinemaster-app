package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.SeatEntity;
import az.turing.cinemamasterapp.domain.entity.UserEntity;
import az.turing.cinemamasterapp.domain.repository.SeatRepository;
import az.turing.cinemamasterapp.domain.repository.UserEntityRepository;
import az.turing.cinemamasterapp.exception.InvalidPasswordConfirmationException;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.mapper.SeatMapper;
import az.turing.cinemamasterapp.mapper.UserMapper;
import az.turing.cinemamasterapp.model.dto.request.CreateSeatRequest;
import az.turing.cinemamasterapp.model.dto.request.CreateUserRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateSeatRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateUserRequest;
import az.turing.cinemamasterapp.model.dto.response.SeatDto;
import az.turing.cinemamasterapp.model.dto.response.UserDto;
import az.turing.cinemamasterapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository repository;
    private final SeatMapper seatMapper;

    public List<SeatDto> findAllSeat() {
        return repository.findAll().stream().map(seatMapper::toDto).collect(Collectors.toList());
    }


    public SeatDto findSeatById(Long id) {
        SeatEntity seatEntity = findById(id);
        return seatMapper.toDto(seatEntity);
    }


    public SeatDto createSeat(CreateSeatRequest request) {
        return seatMapper.toDto(null);
    }


    public SeatDto updateSeatById(Long id, UpdateSeatRequest request) {
        return seatMapper.toDto(null);
    }

    public SeatEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Seat not found with id:" + id));
    }

    public void deleteUserById(Long id) {
        //soft delete
        SeatEntity seatEntity = findById(id);
        seatEntity.setStatus(Status.DELETE);

        //hard delete
        //    entityRepository.deleteById(id);
    }
}