package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

}
