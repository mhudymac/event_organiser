package cz.cvut.fit.tjv.event_organizer.client.api_client;

import cz.cvut.fit.tjv.event_organizer.client.domain.TicketDto;
import org.glassfish.jersey.logging.LoggingFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;

@Component
public class TicketClient {
    private final WebTarget ticketEndpoint;
    private final WebTarget singleEndpointTemplate;
    private WebTarget singleTicketEndpoint;


    public TicketClient(@Value("${server.url}") String apiUrl) {
        var c = ClientBuilder.newClient().register(LoggingFeature.builder().level(Level.ALL).build());
        ticketEndpoint = c.target(apiUrl + "/tickets");
        singleEndpointTemplate = ticketEndpoint.path("/{id}");
    }

    public void setCurrentParticipant(Long id) {
        singleTicketEndpoint = singleEndpointTemplate.resolveTemplate("id", id);
    }

    public void deleteOne() {
        singleTicketEndpoint.request(MediaType.APPLICATION_JSON_TYPE).delete();
    }

    public TicketDto buyTicket(TicketDto ticketDto){
        return ticketEndpoint.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(ticketDto,MediaType.APPLICATION_JSON_TYPE),TicketDto.class);
    }
}
