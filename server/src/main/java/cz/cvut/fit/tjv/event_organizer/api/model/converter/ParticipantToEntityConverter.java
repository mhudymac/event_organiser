package cz.cvut.fit.tjv.event_organizer.api.model.converter;

import cz.cvut.fit.tjv.event_organizer.api.model.ParticipantDto;
import cz.cvut.fit.tjv.event_organizer.domain.Participant;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ParticipantToEntityConverter implements Function<ParticipantDto, Participant> {
    @Override
    public Participant apply(ParticipantDto participantDto) {
        Participant participant = new Participant();
        participant.setID(participantDto.getUsername());
        participant.setParticipantName(participantDto.getRealName());
        participant.setParticipantCity(participantDto.getCity());
        participant.setParticipantCountry(participantDto.getCountry());
        return participant;
    }
}
