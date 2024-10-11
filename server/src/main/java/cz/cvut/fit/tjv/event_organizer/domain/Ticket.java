package cz.cvut.fit.tjv.event_organizer.domain;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.*;

@Entity
public class Ticket implements DomainEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;
    private boolean wasUsed;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "participant_username")
    private Participant participant;

    public Ticket() {

    }

    public void use() {
        this.wasUsed = true;
    }

    public boolean wasUsed() {
        return wasUsed;
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        return getID().equals(((Ticket)obj).getID());
    }

    public Event getEvent() {
        return event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }


}
