package cz.cvut.fit.tjv.event_organizer.client.api_client;

import cz.cvut.fit.tjv.event_organizer.client.domain.ParticipantDto;
import cz.cvut.fit.tjv.event_organizer.client.domain.TicketDto;
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
public class ParticipantClient {
    private final WebTarget participantEndpoint;
    private final WebTarget singleEndpointTemplate;
    private WebTarget singleParticipantEndpoint;

    public ParticipantClient(@Value("${server.url}") String apiUrl) {
        var c = ClientBuilder.newClient().register(LoggingFeature.builder().level(Level.ALL).build());
        participantEndpoint = c.target(apiUrl + "/participants");
        singleEndpointTemplate = participantEndpoint.path("/{id}");
    }

    public void setCurrentParticipant(String id) {
        singleParticipantEndpoint = singleEndpointTemplate.resolveTemplate("id", id);
    }

    public Optional<ParticipantDto> readOne() {
        var response = singleParticipantEndpoint.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() == 200)
            return Optional.of(response.readEntity(ParticipantDto.class));
        else if (response.getStatus() == 404) {
            return Optional.empty();
        } else
            throw new RuntimeException(response.getStatusInfo().getReasonPhrase());
    }


    public ParticipantDto create(ParticipantDto e) {
        return participantEndpoint.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE), ParticipantDto.class);
    }

    public Collection<ParticipantDto> readAll() {
        var res = participantEndpoint.request(MediaType.APPLICATION_JSON_TYPE)
                .get(ParticipantDto[].class);
        return Arrays.asList(res);
    }

    public void updateOne(ParticipantDto e) {
        singleParticipantEndpoint.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE));
    }

    public void deleteOne() {
        singleParticipantEndpoint.request(MediaType.APPLICATION_JSON_TYPE).delete();
    }

    public Collection<TicketDto> getTickets() {
        var res = singleParticipantEndpoint.path("/tickets").request(MediaType.APPLICATION_JSON_TYPE).get(TicketDto[].class);
        return List.of(res);
    }
}
