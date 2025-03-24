package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.MovieEntity;
import az.turing.cinemamasterapp.model.enums.MovieGenre;
import az.turing.cinemamasterapp.model.enums.MovieLanguage;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    boolean existsByNameAndDescriptionAndDirector(String name, String description, String director);

    List<MovieEntity> findByLanguage(MovieLanguage language);

    Optional<MovieEntity> findByName(String movieName);

    Optional<MovieEntity> findByDirector(String director);

    Optional<MovieEntity> findByGenre(MovieGenre movieGenre);

    @Query("SELECT m FROM MovieEntity m WHERE m.releaseDate BETWEEN :now AND :nextDay")
    List<MovieEntity> findByMoviesLast24hours(@Param("now") LocalDateTime now, @Param("nextDay") LocalDateTime nextDay);
}
