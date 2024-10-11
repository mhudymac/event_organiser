package cz.cvut.fit.tjv.event_organizer.dao.jpa;

import cz.cvut.fit.tjv.event_organizer.domain.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrganizerRepository extends CrudRepository<Organizer,String> {
}
