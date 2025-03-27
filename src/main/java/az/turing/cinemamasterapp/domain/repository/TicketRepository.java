package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    boolean existsByTicketNumber(String ticketNumber);

    @Query("select t from TicketEntity t where t.showTime.movie.id =: movieId")
    Page<TicketEntity> allTicketByMovieId(@Param("movieId") Long id, Pageable pageable);

    @Query("select t from TicketEntity t where t.showTime.movie.name =: movieName")
    Page<TicketEntity> allTicketByMovieName(@Param("movieName") String movieName, Pageable pageable);

    @Query("select t from TicketEntity t where t.price<=:price")
    Page<TicketEntity> findByPriceLessThanEqual(@Param("price") Integer price, Pageable pageable);
}
