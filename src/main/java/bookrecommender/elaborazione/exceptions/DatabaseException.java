
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.exceptions;

import java.sql.SQLException;

public class DatabaseException extends RuntimeException {

    public DatabaseException(SQLException e) {
        super(e+" si è verificato un errore nel database");
    }
}
