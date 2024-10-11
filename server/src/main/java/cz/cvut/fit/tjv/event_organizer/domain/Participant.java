package cz.cvut.fit.tjv.event_organizer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class Participant implements DomainEntity<String>{
    @Id
    private String username;
    private String participantName;
    private String participantCity;
    private String participantCountry;

    @OneToMany(mappedBy = "participant")
    private Collection<Ticket> tickets = new HashSet<>();

    public Participant(String username, String participantName, String participantCity, String participantCountry) {
        this.username = username;
        this.participantName = participantName;
        this.participantCity = participantCity;
        this.participantCountry = participantCountry;
    }

    public Participant() {

    }

    @Override
    public String getID() {
        return username;
    }

    public void setID(String username) {this.username = username;}

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String name) {this.participantName = name;}

    public String getParticipantCity() {
        return participantCity;
    }

    public String getParticipantCountry() {
        return participantCountry;
    }

    public void setParticipantCity(String participantCity) {
        this.participantCity = participantCity;
    }

    public void setParticipantCountry(String participantCountry) {
        this.participantCountry = participantCountry;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        return getID().equals(((Participant)obj).getID());
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }
}
