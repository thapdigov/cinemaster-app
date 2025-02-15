package az.turing.cinemamasterapp.domain.entity;

import az.turing.cinemamasterapp.model.enums.PaymentMethod;
import az.turing.cinemamasterapp.model.enums.TicketStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity {

    @Column(name = "ticket_number", nullable = false, unique = true)
    private String ticketNumber;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "showTime", nullable = false)
    private LocalDateTime showTime;

    @Column(name = "purchaseDate", nullable = false)
    private LocalDateTime purchaseDate;

    @Column(name = "status", nullable = false)
    private TicketStatus ticketStatus;

    @Column(name = "paymentMethod", nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;
}
