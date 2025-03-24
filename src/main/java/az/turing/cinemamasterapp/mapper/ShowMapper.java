package az.turing.cinemamasterapp.mapper;

import az.turing.cinemamasterapp.domain.entity.ShowTimeEntity;
import az.turing.cinemamasterapp.model.dto.response.ShowTimeDto;
import org.springframework.stereotype.Component;

@Component
public class ShowMapper implements EntityMapper<ShowTimeEntity, ShowTimeDto> {
    @Override
    public ShowTimeEntity toEnt(ShowTimeDto showTimeDto) {
        return ShowTimeEntity.builder()
                .startTime(showTimeDto.getStartTime())
                .endTime(showTimeDto.getEndTime())
                .showTimeStatus(showTimeDto.getShowTimeStatus())
                .status(showTimeDto.getStatus())
                .movie(showTimeDto.getMovie())
                .cinemaHall(showTimeDto.getCinemaHall())
                .build();
    }

    @Override
    public ShowTimeDto toDto(ShowTimeEntity showTimeEntity) {
        return ShowTimeDto.builder()
                .startTime(showTimeEntity.getStartTime())
                .endTime(showTimeEntity.getEndTime())
                .showTimeStatus(showTimeEntity.getShowTimeStatus())
                .status(showTimeEntity.getStatus())
                .movie(showTimeEntity.getMovie())
                .cinemaHall(showTimeEntity.getCinemaHall())
                .build();
    }
}
