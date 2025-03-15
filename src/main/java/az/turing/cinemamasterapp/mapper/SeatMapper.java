package az.turing.cinemamasterapp.mapper;

import az.turing.cinemamasterapp.domain.entity.SeatEntity;
import az.turing.cinemamasterapp.model.dto.response.SeatDto;

public class SeatMapper implements EntityMapper<SeatEntity, SeatDto> {
    @Override
    public SeatEntity toEnt(SeatDto seatDto) {
        return SeatEntity.builder()
                .row(seatDto.getRow())
                .seatNumber(seatDto.getSeatNumber())
                .type(seatDto.getType())
                .seatStatus(seatDto.getSeatStatus())
                .cinemaHall(seatDto.getCinemaHall())
                .build();
    }

    @Override
    public SeatDto toDto(SeatEntity seatEntity) {
        return SeatDto.builder()
                .row(seatEntity.getRow())
                .seatNumber(seatEntity.getSeatNumber())
                .type(seatEntity.getType())
                .seatStatus(seatEntity.getSeatStatus())
                .cinemaHall(seatEntity.getCinemaHall())
                .build();
    }
}
