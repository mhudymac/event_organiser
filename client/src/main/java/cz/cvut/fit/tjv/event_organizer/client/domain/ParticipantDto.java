package cz.cvut.fit.tjv.event_organizer.client.domain;

public class ParticipantDto {
    private String username;
    private String realName;
    private String city;
    private String country;

    public ParticipantDto() {
    }

    public ParticipantDto(String username, String realName, String city, String country) {
        this.username = username;
        this.realName = realName;
        this.city = city;
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    @Override
    public String toString() {
        return "PostDto{" +
                "username=" + username +
                ", realName='" + realName + '\'' +
                ", latitude='" + city + '\'' +
                ", longitude=" + country +
                '}';
    }
}
