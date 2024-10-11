package cz.cvut.fit.tjv.event_organizer.api.model.converter;

import cz.cvut.fit.tjv.event_organizer.api.model.EventDto;
import cz.cvut.fit.tjv.event_organizer.domain.Event;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EventToDtoConverter implements Function<Event, EventDto> {
    @Override
    public EventDto apply(Event event) {
        return new EventDto(event.getID(),
                            event.getEventTheme(),
                            event.getEventLanguage(),
                            event.getEventDate(),
                            event.getEventCity(),
                            event.getEventCountry(),
                            event.getEventCapacity());
    }
}
