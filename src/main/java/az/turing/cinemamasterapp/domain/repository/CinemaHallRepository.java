package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.CinemaHallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHallEntity, Long> {

    Optional<CinemaHallEntity> findByName(String name);

    boolean existsByName(String name);
}
