package cz.cvut.fit.tjv.event_organizer.business;

import cz.cvut.fit.tjv.event_organizer.api.model.EventDto;
import cz.cvut.fit.tjv.event_organizer.dao.jpa.EventRepository;
import cz.cvut.fit.tjv.event_organizer.domain.Event;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventService extends AbstractCrudService<Event,Long>{
    protected EventService(EventRepository eventRepository) {
        super(eventRepository);
    }

    public List<Event> getByParticipant(String username) {
        return StreamSupport.stream(readAll().spliterator(),false)
                .filter(e -> e.getTickets().stream().noneMatch(t -> t.getParticipant().getID().equals(username))).toList();
    }

    public List<Event> getByOrganizer(String username){
        return StreamSupport.stream(readAll().spliterator(),false)
                .filter(e -> e.getOrganizers().stream().noneMatch(organizer -> organizer.getID().equals(username))).toList();
    }
}
