package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    boolean existsByTicketNumber(String ticketNumber);

    @Query("select t from TicketEntity t where t.showTime.movie.id =: movieId")
    List<TicketEntity> allTicketByMovieId(@Param("movieId") Long id);

    @Query("select t from TicketEntity t where t.showTime.movie.name =: movieName")
    List<TicketEntity> allTicketByMovieName(@Param("movieName") String movieName);

}
