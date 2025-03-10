package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByNameAndDescriptionAndDirector(String name, String description, String director);
}
