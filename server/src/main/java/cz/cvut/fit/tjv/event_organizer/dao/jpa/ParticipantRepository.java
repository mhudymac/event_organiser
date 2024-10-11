package cz.cvut.fit.tjv.event_organizer.dao.jpa;

import cz.cvut.fit.tjv.event_organizer.domain.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant,String> {
}
