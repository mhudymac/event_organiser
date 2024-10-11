package cz.cvut.fit.tjv.event_organizer.api.model.converter;

import cz.cvut.fit.tjv.event_organizer.api.model.EventDto;
import cz.cvut.fit.tjv.event_organizer.domain.Event;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EventToEntityConverter implements Function<EventDto, Event> {
    @Override
    public Event apply(EventDto eventDto) {
        return new Event(eventDto.getId(),
                        eventDto.getTheme(),
                        eventDto.getLanguage(),
                        eventDto.getDate(),
                        eventDto.getCity(),
                        eventDto.getCountry(),
                        eventDto.getCapacity());
    }
}
