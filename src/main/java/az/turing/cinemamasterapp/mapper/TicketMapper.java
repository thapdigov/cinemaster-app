package az.turing.cinemamasterapp.mapper;

import az.turing.cinemamasterapp.domain.entity.TicketEntity;
import az.turing.cinemamasterapp.model.dto.response.TicketDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper implements EntityMapper<TicketEntity, TicketDto> {
    @Override
    public TicketEntity toEnt(TicketDto ticketDto) {
        return TicketEntity.builder()
                .ticketNumber(ticketDto.getTicketNumber())
                .price(ticketDto.getPrice())
                .purchaseDate(ticketDto.getPurchaseDate())
                .ticketStatus(ticketDto.getTicketStatus())
                .paymentMethod(ticketDto.getPaymentMethod())
                .user(ticketDto.getUser())
                .showTime(ticketDto.getShowTime())
                .seat(ticketDto.getSeat())
                .build();

    }

    @Override
    public TicketDto toDto(TicketEntity ticket) {
        return TicketDto.builder()
                .ticketNumber(ticket.getTicketNumber())
                .price(ticket.getPrice())
                .purchaseDate(ticket.getPurchaseDate())
                .ticketStatus(ticket.getTicketStatus())
                .paymentMethod(ticket.getPaymentMethod())
                .user(ticket.getUser())
                .showTime(ticket.getShowTime())
                .seat(ticket.getSeat())
                .build();
    }
}
