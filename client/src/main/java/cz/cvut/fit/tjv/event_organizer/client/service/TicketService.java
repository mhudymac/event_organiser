package cz.cvut.fit.tjv.event_organizer.client.service;

import cz.cvut.fit.tjv.event_organizer.client.api_client.TicketClient;
import cz.cvut.fit.tjv.event_organizer.client.domain.TicketDto;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketClient ticketClient;

    private boolean isSet;

    public TicketService(TicketClient ticketClient) {
        this.ticketClient = ticketClient;
    }

    public void setCurrentTicket(Long id) {
        isSet = true;
        ticketClient.setCurrentParticipant(id);
    }

    public boolean isSet() {
        return isSet;
    }

    public void deleteOne() {
        ticketClient.deleteOne();
    }

    public TicketDto buyTicket(TicketDto ticketDto){
        return ticketClient.buyTicket(ticketDto);
    }
}
