package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {


}
