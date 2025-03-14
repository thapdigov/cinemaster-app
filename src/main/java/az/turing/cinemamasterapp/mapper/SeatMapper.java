package az.turing.cinemamasterapp.mapper;

import az.turing.cinemamasterapp.domain.entity.SeatEntity;
import az.turing.cinemamasterapp.model.dto.response.SeatDto;

public class SeatMapper implements EntityMapper<SeatEntity, SeatDto> {
    @Override
    public SeatEntity toEnt(SeatDto seatDto) {
        return null;
    }

    @Override
    public SeatDto toDto(SeatEntity seatEntity) {
        return null;
    }
}
