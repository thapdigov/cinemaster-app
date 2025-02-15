package az.turing.cinemamasterapp.domain.entity;

import jakarta.persistence.OneToOne;

public class Seat {

    @OneToOne(mappedBy = "seat")
    private Ticket ticket;

}
