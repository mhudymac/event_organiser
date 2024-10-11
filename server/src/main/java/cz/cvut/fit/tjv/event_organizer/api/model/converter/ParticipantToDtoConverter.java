package cz.cvut.fit.tjv.event_organizer.api.model.converter;

import cz.cvut.fit.tjv.event_organizer.api.model.ParticipantDto;
import cz.cvut.fit.tjv.event_organizer.domain.Participant;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ParticipantToDtoConverter implements Function<Participant, ParticipantDto> {
    @Override
    public ParticipantDto apply(Participant participant) {
        ParticipantDto ret = new ParticipantDto();
        ret.setUsername(participant.getID());
        ret.setRealName(participant.getParticipantName());
        ret.setCity(participant.getParticipantCity());
        ret.setCountry(participant.getParticipantCountry());
        return ret;
    }
}
