package cz.cvut.fit.tjv.event_organizer.dao.jpa;

import cz.cvut.fit.tjv.event_organizer.domain.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TicketRepository extends CrudRepository<Ticket,Long> {
}
