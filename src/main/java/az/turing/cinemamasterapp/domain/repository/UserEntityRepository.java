package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u JOIN FETCH u.userTickets WHERE u.id = :id")
    UserEntity findUserWithTickets(@Param("id") Long id);

    Page<UserEntity> findAll(Pageable pageable);

    boolean existsByFirstNameAndLastName(String name, String surname);


}
