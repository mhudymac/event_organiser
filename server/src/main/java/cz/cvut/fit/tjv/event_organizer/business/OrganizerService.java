package cz.cvut.fit.tjv.event_organizer.business;

import cz.cvut.fit.tjv.event_organizer.dao.jpa.OrganizerRepository;
import cz.cvut.fit.tjv.event_organizer.domain.Event;
import cz.cvut.fit.tjv.event_organizer.domain.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class OrganizerService extends AbstractCrudService<Organizer,String> {
    public OrganizerService(OrganizerRepository organizerRepository){
        super (organizerRepository);
    }

    public void addEvent(String username,Event event){
        if(!repository.existsById(username))
            throw new EntityStateException();

        Organizer organizer = readById(username).get();
        if(organizer.getEvents().contains(event))
            throw new EntityStateException(event);

        organizer.getEvents().add(event);
        repository.save(organizer);
    }

    public Collection<Event> getOrganizerEvents(String username){
        if(readById(username).isEmpty())
            throw new EntityStateException();
        return readById(username).get().getEvents();
    }

    public void deleteEvent(String username, Event event){
        if(readById(username).isEmpty())
            throw new EntityStateException();
        Organizer organizer = readById(username).get();
        if(organizer.getEvents().stream().noneMatch(e -> e.equals(event)))
            throw new EntityStateException(event);
        organizer.setEvents(organizer.getEvents().stream().filter(e -> !e.equals(event)).collect(Collectors.toSet()));

        repository.save(organizer);
    }
}
