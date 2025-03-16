package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.repository.ShowTimeRepository;
import az.turing.cinemamasterapp.mapper.ShowMapper;
import az.turing.cinemamasterapp.model.dto.response.ShowTimeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowTimeService {

    private final ShowTimeRepository timeRepository;
    private final ShowMapper timeMapper;


    public List<ShowTimeDto> findAll() {
        return timeRepository.findAll().stream().map(timeMapper::toDto).collect(Collectors.toList());
    }
}
