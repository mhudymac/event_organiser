package cz.cvut.fit.tjv.event_organizer.business;

import cz.cvut.fit.tjv.event_organizer.api.model.TicketDto;
import cz.cvut.fit.tjv.event_organizer.dao.jpa.TicketRepository;
import cz.cvut.fit.tjv.event_organizer.domain.Ticket;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TicketService extends AbstractCrudService<Ticket,Long>{
    public TicketService(TicketRepository ticketRepository){
        super(ticketRepository);
    }

    @Override
    public Ticket create(Ticket entity) throws EntityStateException {

        Iterable<Ticket> allTickets = this.readAll();
        for(var x : allTickets){
            if(x.getParticipant().equals(entity.getParticipant()) && x.getEvent().equals(entity.getEvent()))
                throw new EntityStateException(entity);
        }
        return repository.save(entity);

    }
}
