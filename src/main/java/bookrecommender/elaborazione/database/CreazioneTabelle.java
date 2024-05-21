
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.database;

import bookrecommender.elaborazione.exceptions.DatabaseException;

import java.sql.SQLException;

public final class CreazioneTabelle {

    private CreazioneTabelle() {

    }

    public static void creazione() {

        var id = "id serial NOT NULL,";
        var utenteId = "utente_id int4 NULL,";

        String tabellaUtenti = "CREATE TABLE IF NOT EXISTS public.utenti (" +
                id +
                "username varchar NULL," +
                "password varchar NULL," +
                "CONSTRAINT utente_pkey PRIMARY KEY (id)" +
                ");";

        String tabellaNotifiche = "CREATE TABLE IF NOT EXISTS public.notifiche (" +
                id +
                "testo varchar NULL," +
                utenteId +
                "tempo timestamp NULL," +
                "CONSTRAINT luci_pk1 PRIMARY KEY (id)" +
                ");";

        String tabellaLuci = "CREATE TABLE IF NOT EXISTS public.luci (" +
                id +
                "nome varchar NULL," +
                "acceso bool NULL," +
                "CONSTRAINT luci_pk PRIMARY KEY (id)" +
                ");";

        String tabellaLavatrici = "CREATE TABLE IF NOT EXISTS public.lavatrici (" +
                id +
                "nome varchar NULL," +
                "acceso bool NULL," +
                "lavaggio bool NULL," +
                "temperatura int4 NULL," +
                "tempo int4 NULL," +
                "inizio timestamp NULL," +
                "CONSTRAINT lavatrici_pk PRIMARY KEY (id)" +
                ");";

        String tabellaAsseLuci = "CREATE TABLE IF NOT EXISTS public.assegnamento_luci (" +
                utenteId +
                "luce_id int4 NULL" +
                ");";

        String tabellaAsseLavatrici = "CREATE TABLE IF NOT EXISTS public.assegnamento_lavatrici (" +
                utenteId +
                "lavatrice_id int4 NULL" +
                ");";

        try {

            var connection = Database.getInstance().getConnection();

            try (var statement = connection.createStatement()) {
                statement.executeUpdate(tabellaUtenti);
                statement.executeUpdate(tabellaNotifiche);
                statement.executeUpdate(tabellaLuci);
                statement.executeUpdate(tabellaLavatrici);
                statement.executeUpdate(tabellaAsseLuci);
                statement.executeUpdate(tabellaAsseLavatrici);
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
