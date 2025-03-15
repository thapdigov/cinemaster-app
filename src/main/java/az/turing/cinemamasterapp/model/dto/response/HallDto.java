package az.turing.cinemamasterapp.model.dto.response;

import az.turing.cinemamasterapp.model.enums.HallStatus;
import az.turing.cinemamasterapp.model.enums.HallType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class HallDto {

    private String name;

    private Integer capacity;

    private HallType type;

    private HallStatus hallStatus;
}
