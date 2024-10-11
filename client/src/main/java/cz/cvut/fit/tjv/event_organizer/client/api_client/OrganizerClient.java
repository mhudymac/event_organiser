package cz.cvut.fit.tjv.event_organizer.client.api_client;

import cz.cvut.fit.tjv.event_organizer.client.domain.EventDto;
import cz.cvut.fit.tjv.event_organizer.client.domain.OrganizerDto;
import org.glassfish.jersey.logging.LoggingFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Component
public class OrganizerClient {
    private WebTarget organizerEndpoint;
    private WebTarget singleEndpointTemplate;
    private WebTarget singleOrganizerEndpoint;

    private Long event;

    public OrganizerClient(@Value("${server.url}") String apiUrl) {
        var c = ClientBuilder.newClient().register(LoggingFeature.builder().level(Level.ALL).build());
        organizerEndpoint = c.target(apiUrl + "/organizers");
        singleEndpointTemplate = organizerEndpoint.path("/{id}");
    }

    public void setCurrentOrganizer(String id) {
        singleOrganizerEndpoint = singleEndpointTemplate.resolveTemplate("id", id);
    }

    public void setCurrentEvent(Long eventId){
        event = eventId;
    }

    public Optional<OrganizerDto> readOne() {
        var response = singleOrganizerEndpoint.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() == 200)
            return Optional.of(response.readEntity(OrganizerDto.class));
        else if (response.getStatus() == 404) {
            return Optional.empty();
        } else
            throw new RuntimeException(response.getStatusInfo().getReasonPhrase());
    }


    public OrganizerDto create(OrganizerDto e) {
        return organizerEndpoint.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE), OrganizerDto.class);
    }

    public Collection<OrganizerDto> readAll() {
        var res = organizerEndpoint.request(MediaType.APPLICATION_JSON_TYPE)
                .get(OrganizerDto[].class);
        return Arrays.asList(res);
    }

    public void updateOne(OrganizerDto e) {
        singleOrganizerEndpoint.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE));
    }

    public void deleteOne() {
        singleOrganizerEndpoint.request(MediaType.APPLICATION_JSON_TYPE).delete();
    }

    public void addEvent(EventDto eventDto){
        singleOrganizerEndpoint.path("/addEvent").request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(eventDto, MediaType.APPLICATION_JSON_TYPE), EventDto.class);
    }

    public Collection<EventDto> getEvents() {
        return List.of(singleOrganizerEndpoint.path("/events").request(MediaType.APPLICATION_JSON_TYPE).get(EventDto[].class));
    }

    public void deleteEvent(EventDto eventDto) {
        singleOrganizerEndpoint.path("/event").request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(eventDto,MediaType.APPLICATION_JSON_TYPE), EventDto.class);
    }
}
