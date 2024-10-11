package cz.cvut.fit.tjv.event_organizer.api.model.converter;

import cz.cvut.fit.tjv.event_organizer.api.model.OrganizerDto;
import cz.cvut.fit.tjv.event_organizer.domain.Organizer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrganizerToEntityConverter implements Function<OrganizerDto, Organizer> {
    @Override
    public Organizer apply(OrganizerDto organizerDto) {
        return new Organizer(organizerDto.getUsername(),
                                            organizerDto.getRealName(),
                                            organizerDto.getCity(),
                                            organizerDto.getCountry());
    }
}
