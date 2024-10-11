package cz.cvut.fit.tjv.event_organizer.client.api_client;

import cz.cvut.fit.tjv.event_organizer.client.domain.EventDto;
import org.glassfish.jersey.logging.LoggingFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;

@Component
public class EventClient {
    private final WebTarget eventEndpoint;
    private final WebTarget singleEndpointTemplate;
    private WebTarget singleEventEndpoint;


    public EventClient(@Value("${server.url}") String apiUrl) {
        var c = ClientBuilder.newClient().register(LoggingFeature.builder().level(Level.ALL).build());
        eventEndpoint = c.target(apiUrl + "/events");
        singleEndpointTemplate = eventEndpoint.path("/{id}");
    }

    public void setCurrentEvent(Long id) {
        singleEventEndpoint = singleEndpointTemplate.resolveTemplate("id", id);
    }


    public Collection<EventDto> readAll() {
        var res = eventEndpoint.request(MediaType.APPLICATION_JSON_TYPE)
                .get(EventDto[].class);
        return Arrays.asList(res);
    }

    public Collection<EventDto> readByParticipant(String username){
        WebTarget readByParticipant = eventEndpoint.path("/participant/" + username);
        var res = readByParticipant.request(MediaType.APPLICATION_JSON_TYPE).get(EventDto[].class);
        return Arrays.asList(res);
    }

    public Collection<EventDto> readByOrganizer(String username){
        WebTarget readByOrganizer = eventEndpoint.path("/organizer/" + username);
        var res = readByOrganizer.request(MediaType.APPLICATION_JSON_TYPE).get(EventDto[].class);
        return Arrays.asList(res);
    }

    public EventDto create(EventDto e){
        return eventEndpoint.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE), EventDto.class);
    }

    public Optional<EventDto> readOne() {
        var response = singleEventEndpoint.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() == 200)
            return Optional.of(response.readEntity(EventDto.class));
        else if (response.getStatus() == 404) {
            return Optional.empty();
        } else
            throw new RuntimeException(response.getStatusInfo().getReasonPhrase());
    }

    public void updateOne(EventDto eventDto) {
        singleEventEndpoint.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(eventDto, MediaType.APPLICATION_JSON_TYPE));
    }
}
