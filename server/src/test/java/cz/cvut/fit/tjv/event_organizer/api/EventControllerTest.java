package cz.cvut.fit.tjv.event_organizer.api;

import cz.cvut.fit.tjv.event_organizer.business.EventService;
import cz.cvut.fit.tjv.event_organizer.domain.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventControllerTest {
    @Autowired
    private EventController eventController;

    @Autowired
    private EventService eventService;

    @Test
    public void readByIdTest(){
        Event event = new Event();

        eventService.create(event);

        var ret = eventController.readById(event.getID());
        Assertions.assertNotNull(ret);
        Assertions.assertEquals(ret.getId(), event.getID());
    }
}
