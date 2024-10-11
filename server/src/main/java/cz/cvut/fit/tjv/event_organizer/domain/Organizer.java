package cz.cvut.fit.tjv.event_organizer.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Organizer implements DomainEntity<String> {
    @Id
    private String username;
    private String realName;
    private String city;
    private String country;

    @ManyToMany
    @JoinTable(
            name = "organizer_event",
            joinColumns = @JoinColumn(name = "organizer_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events = new HashSet<>();

    public Organizer(String username, String realName, String city, String country) {
        this.username = username;
        this.realName = realName;
        this.city = city;
        this.country = country;
    }

    public Organizer() {}

    @Override
    public String getID() {
        return username;
    }

    public String getOrganizerCity() {
        return city;
    }

    public String getOrganizerCountry() {
        return country;
    }

    public String getOrganizerName() {
        return realName;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        return getID().equals(((Organizer)obj).getID());
    }
}
