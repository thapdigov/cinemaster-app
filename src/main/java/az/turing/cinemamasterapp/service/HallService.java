package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.CinemaHallEntity;
import az.turing.cinemamasterapp.domain.repository.CinemaHallRepository;
import az.turing.cinemamasterapp.exception.AlreadyExistsException;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.mapper.HallMapper;
import az.turing.cinemamasterapp.model.dto.request.CreateHallRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateHallRequest;
import az.turing.cinemamasterapp.model.dto.response.HallDto;
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
public class HallService {

    private final CinemaHallRepository hallRepository;
    private final HallMapper hallMapper;


    public Page<HallDto> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<CinemaHallEntity> entityPage = hallRepository.findAll(pageable);
        List<HallDto> dtoList = entityPage.stream().filter(
                cinemaHallEntity -> cinemaHallEntity.getStatus() != Status.DELETE).map(hallMapper::toDto).toList();
        return new PageImpl<>(dtoList, pageable, dtoList.size());
    }

    public HallDto findHallById(Long id) {
        return hallMapper.toDto(findById(id));
    }


    public void deleteHall(Long id) {
        CinemaHallEntity hallEntity = findById(id);
        hallEntity.setStatus(Status.DELETE);
        hallRepository.save(hallEntity);
    }


    public HallDto createHall(CreateHallRequest request) {

        if (hallRepository.existsByName(request.getName())) {
            throw new AlreadyExistsException("Cinema Hall has already exists with name: " + request.getName());
        }
        CinemaHallEntity cinemaHall = hallMapper.toEnt(request);
        CinemaHallEntity savedHall = hallRepository.save(cinemaHall);
        return hallMapper.toDto(savedHall);
    }

    public HallDto update(Long id, UpdateHallRequest request) {

        CinemaHallEntity hall = findById(id);
        hall.setName(request.getName());
        hall.setCapacity(request.getCapacity());
        hall.setType(request.getType());
        hall.setHallStatus(request.getHallStatus());
        hall.setStatus(request.getStatus());

        CinemaHallEntity updatedHall = hallRepository.save(hall);
        return hallMapper.toDto(updatedHall);
    }

    public CinemaHallEntity findById(Long id) {
        return hallRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CinemaHall not found with id: " + id));
    }
}
