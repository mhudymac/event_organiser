package cz.cvut.fit.tjv.event_organizer.business;

/**
 * An exception indicating problem related to existence of an entity.
 */
public class EntityStateException extends RuntimeException {
    public EntityStateException() {
    }

    public <E> EntityStateException(E entity) {
        super("Illegal state of entity " + entity);
    }

    public EntityStateException(String s) {
        super(s);
    }
}
