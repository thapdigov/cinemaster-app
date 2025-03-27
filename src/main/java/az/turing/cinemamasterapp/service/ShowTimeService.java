package az.turing.cinemamasterapp.service;

import az.turing.cinemamasterapp.domain.entity.CinemaHallEntity;
import az.turing.cinemamasterapp.domain.entity.MovieEntity;
import az.turing.cinemamasterapp.domain.entity.ShowTimeEntity;
import az.turing.cinemamasterapp.domain.repository.CinemaHallRepository;
import az.turing.cinemamasterapp.domain.repository.MovieRepository;
import az.turing.cinemamasterapp.domain.repository.ShowTimeRepository;
import az.turing.cinemamasterapp.exception.NotFoundException;
import az.turing.cinemamasterapp.mapper.ShowMapper;
import az.turing.cinemamasterapp.model.dto.request.CreateShowTimeRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateShowTimeRequest;
import az.turing.cinemamasterapp.model.dto.response.ShowTimeDto;
import az.turing.cinemamasterapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowTimeService {

    private final ShowTimeRepository timeRepository;
    private final MovieRepository movieRepository;
    private final CinemaHallRepository hallRepository;
    private final ShowMapper timeMapper;


    public Page<ShowTimeDto> findAll(Pageable pageable) {
        Page<ShowTimeEntity> entityPage = timeRepository.findAll(pageable);
        return entityPage.map(timeMapper::toDto);
    }

    public ShowTimeDto findShowById(Long id) {
        ShowTimeEntity showTime = timeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ShowTime not found with id: " + id));
        return timeMapper.toDto(showTime);
    }

    public ShowTimeDto createShowTime(CreateShowTimeRequest request) {

        ShowTimeEntity timeEntity = new ShowTimeEntity();

        MovieEntity movieEntity = movieRepository.findById(request.getMovieId()).
                orElseThrow(() -> new NotFoundException("Movie not found with id: " + request.getMovieId()));
        CinemaHallEntity hallEntity = hallRepository.findById(request.getHallId()).
                orElseThrow(() -> new NotFoundException("CinemaHall not found with id: " + request.getHallId()));


        timeEntity.setStartTime(request.getStartTime());
        timeEntity.setEndTime(request.getEndTime());
        timeEntity.setShowTimeStatus(request.getShowTimeStatus());
        timeEntity.setStatus(request.getStatus());
        timeEntity.setMovie(movieEntity);
        timeEntity.setCinemaHall(hallEntity);

        return timeMapper.toDto(timeRepository.save(timeEntity));
    }

    public ShowTimeDto updateShowTime(Long id, UpdateShowTimeRequest request) {

        ShowTimeEntity timeEntity = timeRepository.findById(id).
                orElseThrow(() -> new NotFoundException("ShowTime not found with id: " + id));

        MovieEntity movieEntity = movieRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Movie not found with id: " + request.getMovieId()));

        CinemaHallEntity hallEntity = hallRepository.findById(request.getHallId()).
                orElseThrow(() -> new NotFoundException("CinemaHall not found with id: " + request.getHallId()));


        timeEntity.setStartTime(request.getStartTime());
        timeEntity.setEndTime(request.getEndTime());
        timeEntity.setShowTimeStatus(request.getShowTimeStatus());
        timeEntity.setStatus(request.getStatus());
        timeEntity.setMovie(movieEntity);
        timeEntity.setCinemaHall(hallEntity);

        return timeMapper.toDto(timeRepository.save(timeEntity));
    }

    public void deleteById(Long id) {

        ShowTimeEntity timeEntity = timeRepository.findById(id).
                orElseThrow(() -> new NotFoundException("ShowTime not found with id: " + id));
        timeEntity.setStatus(Status.DELETE);
    }
}
