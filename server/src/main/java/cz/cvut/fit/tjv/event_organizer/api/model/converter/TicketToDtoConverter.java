package cz.cvut.fit.tjv.event_organizer.api.model.converter;

import cz.cvut.fit.tjv.event_organizer.api.model.TicketDto;
import cz.cvut.fit.tjv.event_organizer.domain.Ticket;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TicketToDtoConverter implements Function<Ticket, TicketDto> {

    @Override
    public TicketDto apply(Ticket ticket) {
        TicketDto ret = new TicketDto();
        ret.setId(ticket.getID());
        ret.setParticipantUsername(ticket.getParticipant().getID());
        ret.setEventId(ticket.getEvent().getID());
        ret.setEventCity(ticket.getEvent().getEventCity());
        ret.setEventCountry(ticket.getEvent().getEventCountry());
        ret.setEventDate(ticket.getEvent().getEventDate());
        ret.setEventLanguage(ticket.getEvent().getEventLanguage());
        ret.setEventTheme(ticket.getEvent().getEventTheme());
        return ret;
    }
}
