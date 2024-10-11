package cz.cvut.fit.tjv.event_organizer.api;

import cz.cvut.fit.tjv.event_organizer.api.model.EventDto;
import cz.cvut.fit.tjv.event_organizer.business.AbstractCrudService;
import cz.cvut.fit.tjv.event_organizer.business.EventService;
import cz.cvut.fit.tjv.event_organizer.domain.Event;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController extends AbstractCrudController<Event, EventDto, Long> {
    public EventController(AbstractCrudService<Event, Long> service, Function<Event, EventDto> toDtoConverter, Function<EventDto, Event> toEntityConverter) {
        super(service, toDtoConverter, toEntityConverter);
    }

    @GetMapping("/participant/{username}")
    public Collection<EventDto> getByParticipant(@PathVariable String username){
        return ((EventService)service).getByParticipant(username).stream().map(toDtoConverter).collect(Collectors.toList());
    }

    @GetMapping("/organizer/{username}")
    public Collection<EventDto> getByOrganizer(@PathVariable String username){
        return ((EventService)service).getByOrganizer(username).stream().map(toDtoConverter).collect(Collectors.toList());
    }
}
