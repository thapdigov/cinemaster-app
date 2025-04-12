package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.MovieEntity;
import az.turing.cinemamasterapp.model.enums.MovieGenre;
import az.turing.cinemamasterapp.model.enums.MovieLanguage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<MovieEntity> findByLanguage(MovieLanguage language, Pageable pageable);

    Optional<MovieEntity> findByName(String movieName);

    Page<MovieEntity> findListByName(String movieName, Pageable pageable);

    Page<MovieEntity> findByDirector(String director, Pageable pageable);

    Page<MovieEntity> findByGenre(MovieGenre movieGenre, Pageable pageable);

    @Query("SELECT m FROM MovieEntity m WHERE m.releaseDate BETWEEN :now AND :nextDay")
    Page<MovieEntity> findByMoviesLast24hours
            (@Param("now") LocalDateTime now, @Param("nextDay") LocalDateTime nextDay, Pageable pageable);
}
