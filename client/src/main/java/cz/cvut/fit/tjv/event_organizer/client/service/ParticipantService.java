package cz.cvut.fit.tjv.event_organizer.client.service;

import cz.cvut.fit.tjv.event_organizer.client.api_client.ParticipantClient;
import cz.cvut.fit.tjv.event_organizer.client.domain.ParticipantDto;
import cz.cvut.fit.tjv.event_organizer.client.domain.TicketDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ParticipantService {
    private ParticipantClient participantClient;
    private boolean isSet;


    public ParticipantService(ParticipantClient participantClient) {
        this.participantClient = participantClient;
    }

    public ParticipantDto create(ParticipantDto e) {
        return participantClient.create(e);
    }

    public Collection<ParticipantDto> readAll() {
        return participantClient.readAll();
    }

    public void setCurrentParticipant(String id) {
        isSet = true;
        participantClient.setCurrentParticipant(id);
    }

    public boolean isSet() {
        return isSet;
    }

    public Optional<ParticipantDto> readOne() {
        return participantClient.readOne();
    }


    public void update(ParticipantDto e) {
        participantClient.updateOne(e);
    }


    public void deleteOne() {
        participantClient.deleteOne();
    }

    public Collection<TicketDto> getTickets() {
        return participantClient.getTickets();
    }
}
