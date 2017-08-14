package de.thaso.orwo.db.common;

/**
 * ApplicationException
 *
 * @author thaler
 * @since 2017-07-13
 */
public class DatabaseException extends RuntimeException {

    private DatabaseError databaseError;

    public DatabaseException(final DatabaseError databaseError, final String message) {
        super(message);
        this.databaseError = databaseError;
    }

    public DatabaseError getDatabaseError() {
        return databaseError;
    }
}
