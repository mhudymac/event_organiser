package cz.cvut.fit.tjv.event_organizer.api;

import cz.cvut.fit.tjv.event_organizer.api.model.EventDto;
import cz.cvut.fit.tjv.event_organizer.api.model.OrganizerDto;
import cz.cvut.fit.tjv.event_organizer.api.model.converter.EventToDtoConverter;
import cz.cvut.fit.tjv.event_organizer.api.model.converter.EventToEntityConverter;
import cz.cvut.fit.tjv.event_organizer.business.AbstractCrudService;
import cz.cvut.fit.tjv.event_organizer.business.OrganizerService;
import cz.cvut.fit.tjv.event_organizer.domain.Event;
import cz.cvut.fit.tjv.event_organizer.domain.Organizer;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/organizers")
public class OrganizerController extends AbstractCrudController<Organizer, OrganizerDto,String> {
    public OrganizerController(AbstractCrudService<Organizer, String> service, Function<Organizer, OrganizerDto> toDtoConverter, Function<OrganizerDto, Organizer> toEntityConverter) {
        super(service, toDtoConverter, toEntityConverter);
    }
    @PostMapping("/{username}/addEvent")
    public void addEvent(@PathVariable String username,@RequestBody EventDto eventDto){
        EventToEntityConverter eventToEntityConverter = new EventToEntityConverter();
        ((OrganizerService)service).addEvent(username,eventToEntityConverter.apply(eventDto));
    }

    @GetMapping("/{username}/events")
    public Collection<EventDto> getEvents(@PathVariable String username){
        EventToDtoConverter eventToDtoConverter = new EventToDtoConverter();
        return ((OrganizerService)service).getOrganizerEvents(username).stream().map(eventToDtoConverter).collect(Collectors.toList());
    }

    @PostMapping("/{username}/event")
    public void deleteEvent(@PathVariable String username, @RequestBody EventDto eventDto){
        EventToEntityConverter eventToEntityConverter = new EventToEntityConverter();
        ((OrganizerService)service).deleteEvent(username, eventToEntityConverter.apply(eventDto));
    }
}
