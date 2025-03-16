package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.ShowTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTimeEntity, Long> {

}
