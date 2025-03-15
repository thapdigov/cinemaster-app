package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.CinemaHallEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaHallRepository extends JpaRepository<CinemaHallEntity, Long> {
}
