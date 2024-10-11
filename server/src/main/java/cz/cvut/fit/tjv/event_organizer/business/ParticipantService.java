package cz.cvut.fit.tjv.event_organizer.business;

import cz.cvut.fit.tjv.event_organizer.api.model.TicketDto;
import cz.cvut.fit.tjv.event_organizer.dao.jpa.ParticipantRepository;
import cz.cvut.fit.tjv.event_organizer.domain.Participant;
import cz.cvut.fit.tjv.event_organizer.domain.Ticket;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ParticipantService extends AbstractCrudService<Participant, String> {
    public ParticipantService(ParticipantRepository participantRepository) {
        super(participantRepository);
    }

    public Collection<Ticket> getParticipantsTickets(String id){
        if(readById(id).isEmpty())
            throw new EntityStateException();
        return readById(id).get().getTickets();
    }
}