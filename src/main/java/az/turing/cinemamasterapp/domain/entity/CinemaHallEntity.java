package az.turing.cinemamasterapp.domain.entity;

import az.turing.cinemamasterapp.model.enums.HallStatus;
import az.turing.cinemamasterapp.model.enums.HallType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "cinemahall")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CinemaHallEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private HallType type;

    @Column(name = "hall_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private HallStatus hallStatus;

    @OneToMany(mappedBy = "cinemaHall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SeatEntity> seats;

    @OneToMany(mappedBy = "cinemaHall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShowTimeEntity> showTimes;
}
