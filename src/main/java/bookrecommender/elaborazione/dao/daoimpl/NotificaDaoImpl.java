
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.dao.daoimpl;

import bookrecommender.elaborazione.dao.NotificaDao;
import bookrecommender.elaborazione.database.Database;
import bookrecommender.elaborazione.entities.Notifica;
import bookrecommender.elaborazione.exceptions.DatabaseException;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class NotificaDaoImpl implements NotificaDao<Notifica> {

    private final Database database;

    public NotificaDaoImpl() {

        database=Database.getInstance();
    }

    @Override
    public List<Notifica> readAll(Integer utenteId) {

        List<Notifica> notifiche=new ArrayList<>();

        try {
            var connection = database.getConnection();

            try (var resultSet = connection.createStatement().executeQuery(
                    "SELECT * FROM notifiche WHERE utente_id='"+utenteId+"' ORDER BY id")) {

                while (resultSet.next()) {
                    notifiche.add(new Notifica(resultSet.getInt("id"),
                            resultSet.getString("testo"),
                            resultSet.getInt("utente_id"),
                            LocalDateTime.ofInstant(Instant.ofEpochMilli(
                                    resultSet.getTimestamp("tempo").getTime()),
                                    ZoneOffset.UTC)));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return notifiche;
    }

    @Override
    public Integer create(Notifica notifica, Integer utenteId) {

        Integer id=null;

        var timestamp = new Timestamp((notifica.getLocalDateTime().toInstant(ZoneOffset.UTC).toEpochMilli()));

        try {
            var connection = database.getConnection();

            try (var pstmt = connection.prepareStatement(
                    "INSERT INTO notifiche(testo, utente_id,tempo) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setString(1, notifica.getTesto());
                pstmt.setInt(2, notifica.getUtenteId());
                pstmt.setTimestamp(3,timestamp);

                int affectedRows = pstmt.executeUpdate();

                if(affectedRows > 0) {
                    id=setId(pstmt);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return id;
    }

    private static Integer setId(PreparedStatement pstmt) throws SQLException {

        Integer id=0;

        try(ResultSet rs = pstmt.getGeneratedKeys()) {
            if(rs.next()) {
                id = rs.getInt(1);
            }
        }

        return id;
    }
}
