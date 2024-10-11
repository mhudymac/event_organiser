package cz.cvut.fit.tjv.event_organizer.business;

import cz.cvut.fit.tjv.event_organizer.ApiMain;
import cz.cvut.fit.tjv.event_organizer.dao.jpa.OrganizerRepository;
import cz.cvut.fit.tjv.event_organizer.domain.Event;
import cz.cvut.fit.tjv.event_organizer.domain.Organizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ApiMain.class)
public class OrganizerServiceTest {

    @Autowired
    OrganizerService organizerService;

    @MockBean
    OrganizerRepository organizerRepository;

    @MockBean
    EventService eventService;

    @Test
    void addGetEvents(){
        Organizer organizer = new Organizer("test","Test","Prague","Czechia");
        Event event1 = new Event(1L,"games","Slovak",new Date(2023, Calendar.MARCH,3),"city","country",100);
        Event event2 = new Event(2L,"movies","Czech",new Date(2023, Calendar.JUNE,3),"city2","country2",100);

        Mockito.when(organizerRepository.findById(organizer.getID())).thenReturn(Optional.of(organizer));
        Mockito.when(organizerRepository.existsById(organizer.getID())).thenReturn(true);

        organizerService.addEvent("test",event1);
        Assertions.assertEquals(organizerService.getOrganizerEvents(organizer.getID()).toArray()[0],event1);
        Assertions.assertFalse(organizerService.getOrganizerEvents(organizer.getID()).contains(event2));
    }
}
