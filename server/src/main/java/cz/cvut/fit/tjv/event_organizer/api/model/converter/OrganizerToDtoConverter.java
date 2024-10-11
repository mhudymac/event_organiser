package cz.cvut.fit.tjv.event_organizer.api.model.converter;

import cz.cvut.fit.tjv.event_organizer.api.model.OrganizerDto;
import cz.cvut.fit.tjv.event_organizer.domain.Organizer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrganizerToDtoConverter implements Function<Organizer, OrganizerDto> {
    @Override
    public OrganizerDto apply(Organizer organizer) {
        OrganizerDto ret = new OrganizerDto();
        ret.setUsername(organizer.getID());
        ret.setRealName(organizer.getOrganizerName());
        ret.setCity(organizer.getOrganizerCity());
        ret.setCountry(organizer.getOrganizerCountry());
        return ret;
    }
}
