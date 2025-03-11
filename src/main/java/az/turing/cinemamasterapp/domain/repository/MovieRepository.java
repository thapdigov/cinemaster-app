package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByNameAndDescriptionAndDirector(String name, String description, String director);
    Optional<Movie> findByName(String movieName);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate BETWEEN :now AND :nextDay")
    List<Movie> findByMoviesLast24hours(@Param("now") LocalDateTime now, @Param("nextDay") LocalDateTime nextDay);
}
