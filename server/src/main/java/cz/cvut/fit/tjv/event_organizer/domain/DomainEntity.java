package cz.cvut.fit.tjv.event_organizer.domain;

import java.io.Serializable;

public interface DomainEntity<ID> extends Serializable {
    ID getID();
}
