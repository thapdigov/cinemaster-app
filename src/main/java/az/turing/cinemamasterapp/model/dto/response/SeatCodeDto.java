package az.turing.cinemamasterapp.model.dto.response;

import lombok.Data;


@Data
public class SeatCodeDto {
    private String rowNumber;
    public SeatCodeDto(String row, Integer seatNumber) {
        this.rowNumber = row + seatNumber;
    }
}
