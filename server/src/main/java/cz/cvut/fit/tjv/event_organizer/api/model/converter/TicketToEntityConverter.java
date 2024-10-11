package cz.cvut.fit.tjv.event_organizer.api.model.converter;

import cz.cvut.fit.tjv.event_organizer.api.model.TicketDto;
import cz.cvut.fit.tjv.event_organizer.business.EventService;
import cz.cvut.fit.tjv.event_organizer.business.ParticipantService;
import cz.cvut.fit.tjv.event_organizer.domain.Event;
import cz.cvut.fit.tjv.event_organizer.domain.Participant;
import cz.cvut.fit.tjv.event_organizer.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class TicketToEntityConverter implements Function<TicketDto, Ticket> {
    private final EventService eventService;
    private final ParticipantService participantService;

    public TicketToEntityConverter(EventService eventService, ParticipantService participantService) {
        this.eventService = eventService;
        this.participantService = participantService;
    }

    @Override
    public Ticket apply(TicketDto ticketDto) {
        Optional<Event> event = eventService.readById(ticketDto.getEventId());
        Optional<Participant> participant = participantService.readById(ticketDto.getParticipantUsername());
        Ticket ret = new Ticket();
        ret.setEvent(event.get());
        ret.setParticipant(participant.get());

        return ret;
    }
}
