package cz.cvut.fit.tjv.event_organizer.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Event implements DomainEntity<Long>{
    @Id
    @GeneratedValue
    private Long id;
    private String theme;
    private String language;
    private Date date;
    private String city;
    private String country;
    private int capacity;

    @ManyToMany
    @JoinTable(
            name = "organizer_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "organizer_id")
    )
    private Set<Organizer> organizers = new HashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<Ticket> tickets = new HashSet<>();

    public Event(Long eventId, String theme, String language, Date date, String city, String country, int capacity) {
        this.id = eventId;
        this.theme = theme;
        this.language = language;
        this.date = date;
        this.city = city;
        this.country = country;
        this.capacity = capacity;
    }

    public Event() {}

    @Override
    public Long getID() {
        return id;
    }

    public String getEventTheme() {
        return theme;
    }

    public String getEventLanguage() {
        return language;
    }

    public Date getEventDate() {
        return date;
    }

    public String getEventCity() {
        return city;
    }

    public String getEventCountry() {
        return country;
    }

    public int getEventCapacity() {
        return capacity;
    }

    public Set<Organizer> getOrganizers() {
        return organizers;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }


//    @Override
//    public boolean equals(Object obj) {
//        if(this == obj) return true;
//        if(obj == null || getClass() != obj.getClass()) return false;
//
//        return getID().equals(((Event)obj).getID());
//    }

    @Override
    public boolean equals(Object obj) {
        return getID().equals(((Event)obj).getID());
    }
}
