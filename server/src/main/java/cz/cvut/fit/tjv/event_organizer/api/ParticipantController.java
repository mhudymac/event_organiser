package cz.cvut.fit.tjv.event_organizer.api;

import cz.cvut.fit.tjv.event_organizer.api.model.EventDto;
import cz.cvut.fit.tjv.event_organizer.api.model.ParticipantDto;
import cz.cvut.fit.tjv.event_organizer.api.model.TicketDto;
import cz.cvut.fit.tjv.event_organizer.api.model.converter.TicketToDtoConverter;
import cz.cvut.fit.tjv.event_organizer.business.AbstractCrudService;
import cz.cvut.fit.tjv.event_organizer.business.ParticipantService;
import cz.cvut.fit.tjv.event_organizer.business.TicketService;
import cz.cvut.fit.tjv.event_organizer.domain.Participant;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/participants")
public class ParticipantController extends AbstractCrudController<Participant, ParticipantDto, String> {
    public ParticipantController(AbstractCrudService<Participant, String> service, Function<Participant, ParticipantDto> toDtoConverter, Function<ParticipantDto, Participant> toEntityConverter, TicketService ticketService) {
        super(service, toDtoConverter, toEntityConverter);
    }
    @GetMapping("/{id}/tickets")
    public Collection<TicketDto> getParticipantsTickets(@PathVariable String id){
        TicketToDtoConverter ticketToDtoConverter = new TicketToDtoConverter();
        return ((ParticipantService)service).getParticipantsTickets(id).stream().map(ticketToDtoConverter).collect(Collectors.toList());
    }

}
