package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.CinemaHallEntity;
import az.turing.cinemamasterapp.domain.repository.CinemaHallRepository;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.mapper.HallMapper;
import az.turing.cinemamasterapp.model.dto.request.CreateHallRequest;
import az.turing.cinemamasterapp.model.dto.response.HallDto;
import az.turing.cinemamasterapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HallService {

    private final CinemaHallRepository hallRepository;
    private final HallMapper hallMapper;


    public List<HallDto> findAll() {
        return hallRepository.findAll().stream().map(hallMapper::toDto).collect(Collectors.toList());
    }

    public HallDto findHallById(Long id) {
        return hallMapper.toDto(findById(id));
    }


    public void deleteHall(Long id) {
        CinemaHallEntity hallEntity = findById(id);
        hallEntity.setStatus(Status.DELETE);
    }


    public CinemaHallEntity findById(Long id) {
        return hallRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CinemaHall not found with id: " + id));
    }

    public HallDto createHall(CreateHallRequest request) {
        return null;
    }
}
