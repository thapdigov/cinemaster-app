package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

}
