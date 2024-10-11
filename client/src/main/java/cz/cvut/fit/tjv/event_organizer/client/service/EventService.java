package cz.cvut.fit.tjv.event_organizer.client.service;

import cz.cvut.fit.tjv.event_organizer.client.api_client.EventClient;
import cz.cvut.fit.tjv.event_organizer.client.domain.EventDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EventService {
    private final EventClient eventClient;
    private boolean isSet;


    public EventService(EventClient eventClient) {
        this.eventClient = eventClient;
    }

    public Collection<EventDto> readAll() {
        return eventClient.readAll();
    }

    public void setCurrentEvent(Long id) {
        isSet = true;
        eventClient.setCurrentEvent(id);
    }

    public boolean isSet() {
        return isSet;
    }

    public Collection<EventDto> readByParticipant(String username){
        return eventClient.readByParticipant(username);
    }
    public Collection<EventDto> readByOrganizer(String username){
        return eventClient.readByOrganizer(username);
    }

    public EventDto create(EventDto e){
        return eventClient.create(e);
    }

    public Optional<EventDto> readOne() {
        return eventClient.readOne();
    }

    public void update(EventDto eventDto) {
        eventClient.updateOne(eventDto);
    }
}
