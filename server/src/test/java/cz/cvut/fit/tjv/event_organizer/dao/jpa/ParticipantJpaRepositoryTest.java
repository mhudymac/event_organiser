package cz.cvut.fit.tjv.event_organizer.dao.jpa;


import cz.cvut.fit.tjv.event_organizer.domain.Participant;
import cz.cvut.fit.tjv.event_organizer.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ParticipantJpaRepositoryTest {

    @Autowired
    private ParticipantRepository participantRepository;

    @Test
    void findParticipantById(){
        Participant p = new Participant("test","Test","Prague","Czechia");
        participantRepository.save(p);
        Participant p1 = participantRepository.findById("test").stream().findFirst().get();
        assertEquals(p1, p);
    }
}
