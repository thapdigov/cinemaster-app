package az.turing.cinemamasterapp.mapper;

import az.turing.cinemamasterapp.domain.entity.CinemaHallEntity;
import az.turing.cinemamasterapp.model.dto.response.HallDto;
import org.springframework.stereotype.Component;

@Component
public class HallMapper implements EntityMapper<CinemaHallEntity, HallDto> {
    @Override
    public CinemaHallEntity toEnt(HallDto hallDto) {
        return CinemaHallEntity.builder()
                .name(hallDto.getName())
                .capacity(hallDto.getCapacity())
                .type(hallDto.getType())
                .hallStatus(hallDto.getHallStatus())
                .build();
    }

    @Override
    public HallDto toDto(CinemaHallEntity cinemaHallEntity) {
        return HallDto.builder()
                .name(cinemaHallEntity.getName())
                .capacity(cinemaHallEntity.getCapacity())
                .type(cinemaHallEntity.getType())
                .hallStatus(cinemaHallEntity.getHallStatus())
                .status(cinemaHallEntity.getStatus())
                .build();
    }
}
