package cz.cvut.fit.tjv.event_organizer.dao.jpa;

import cz.cvut.fit.tjv.event_organizer.domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
