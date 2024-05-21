
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Database {

    private static final Database instance = new Database();

    private static final String URL = "jdbc:postgresql://localhost/postgres";

    private Connection connessione;

    private final Properties props;


    private Database() {

        props = new Properties();

        props.setProperty("user", "postgres");
        props.setProperty("password", "root");

    }

    public static Database getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {

        if(connessione==null) {
            connessione=DriverManager.getConnection(URL,props);
        }

        return connessione;
    }

    public void closeConnection() throws SQLException {
        connessione.close();
    }

}
