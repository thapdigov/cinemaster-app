package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.Seat;
import az.turing.cinemamasterapp.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {


}
