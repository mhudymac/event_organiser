package cz.cvut.fit.tjv.event_organizer.client.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EventDto {
    private Long id;
    private String theme;
    private String language;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String city;
    private String country;
    private int capacity;

    public EventDto(){};
    public EventDto(Long id, String theme, String language, Date date, String city, String country, int capacity) {
        this.id = id;
        this.theme = theme;
        this.language = language;
        this.date = date;
        this.city = city;
        this.country = country;
        this.capacity = capacity;
    }

    public EventDto(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", language='" + language + '\'' +
                ", date=" + date +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
