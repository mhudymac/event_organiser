package cz.cvut.fit.tjv.event_organizer.client.service;

import cz.cvut.fit.tjv.event_organizer.client.api_client.OrganizerClient;
import cz.cvut.fit.tjv.event_organizer.client.domain.EventDto;
import cz.cvut.fit.tjv.event_organizer.client.domain.OrganizerDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrganizerService {
    private OrganizerClient organizerClient;
    private boolean isSet;


    public OrganizerService(OrganizerClient organizerClient) {
        this.organizerClient = organizerClient;
    }

    public OrganizerDto create(OrganizerDto e) {
        return organizerClient.create(e);
    }

    public Collection<OrganizerDto> readAll() {
        return organizerClient.readAll();
    }

    public void setCurrentOrganizer(String id) {
        isSet = true;
        organizerClient.setCurrentOrganizer(id);
    }

    public boolean isSet() {
        return isSet;
    }


    public Optional<OrganizerDto> readOne() {
        return organizerClient.readOne();
    }


    public void update(OrganizerDto e) {
        organizerClient.updateOne(e);
    }


    public void deleteOne() {
        organizerClient.deleteOne();
    }

    public void addEvent(EventDto eventDto){
        organizerClient.addEvent(eventDto);
    }

    public Collection<EventDto> getEvents() {
        return organizerClient.getEvents();
    }

    public void deleteEvent(EventDto eventDto){
        organizerClient.deleteEvent(eventDto);
    }
}
