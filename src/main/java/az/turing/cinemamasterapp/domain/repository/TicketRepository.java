package az.turing.cinemamasterapp.domain.repository;

import az.turing.cinemamasterapp.domain.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    boolean existsByTicketNumber(String ticketNumber);
}
