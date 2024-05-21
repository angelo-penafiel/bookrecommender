
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.dao.daoimpl;

import bookrecommender.elaborazione.database.Database;
import bookrecommender.elaborazione.entities.Lavatrice;
import bookrecommender.elaborazione.exceptions.DatabaseException;
import bookrecommender.elaborazione.factory.Dispositivo;
import bookrecommender.elaborazione.factory.DispositivoEnum;
import bookrecommender.elaborazione.factory.DispositivoFactory;
import bookrecommender.elaborazione.dao.LavatriceDao;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class LavatriceDaoImpl implements LavatriceDao<Lavatrice> {

    //singleton (private)
    private final Database database;

    public LavatriceDaoImpl() {
        database=Database.getInstance();
    }

    @Override
    public List<Lavatrice> readAll(Integer utenteId) {

        List<Lavatrice> lavatrici=new ArrayList<>();
        List<Integer> lavatriciId=readIdAssegnamenti(utenteId);

        if(!lavatriciId.isEmpty()) {
            for(Integer lavatriceId:lavatriciId) {
                lavatrici.add(read(lavatriceId));
            }
        }

        return lavatrici;
    }

    public List<Integer> readIdAssegnamenti(Integer utenteId) {

        List<Integer> lavatriciId=new ArrayList<>();

        try {
            var connection = database.getConnection();

            try (var resultSet = connection.createStatement().executeQuery(
                    "SELECT * FROM assegnamento_lavatrici WHERE utente_id='"+utenteId+"' " +
                            "ORDER BY lavatrice_id")) {

                while (resultSet.next()) {
                    lavatriciId.add(resultSet.getInt("lavatrice_id"));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return lavatriciId;
    }

    @Override
    public Lavatrice read(Integer id) {

        Dispositivo dispositivo = null;

        try {
            var connection = database.getConnection();

                try (var resultSet = connection.createStatement().executeQuery(
                        "SELECT * FROM lavatrici WHERE id='"+id+"' ORDER BY id")) {
                    while (resultSet.next()) {

                        Integer tempo=resultSet.getInt("tempo");
                        var timestamp=resultSet.getTimestamp("inizio");

                        LocalDateTime inizio=setInizio(timestamp);
                        LocalDateTime fine=setFine(timestamp,inizio,tempo);

                        List<Object> parametri = new ArrayList<>();
                        parametri.add(resultSet.getInt("id"));
                        parametri.add(resultSet.getString("nome"));
                        parametri.add(resultSet.getBoolean("acceso"));
                        parametri.add(resultSet.getInt("temperatura"));
                        parametri.add(tempo);
                        parametri.add(inizio);
                        parametri.add(fine);

                        dispositivo = DispositivoFactory.getDispositivo(
                                DispositivoEnum.LAVATRICE_CON_ID, parametri);

                    }
                }


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return (Lavatrice) dispositivo;
    }

    private static LocalDateTime setInizio(Timestamp timestamp) {

        LocalDateTime inizio;

        if(timestamp!=null) {
            inizio=LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(timestamp.getTime()), ZoneOffset.UTC);
        }

        else {
            inizio=null;
        }

        return inizio;
    }

    private static LocalDateTime setFine(Timestamp timestamp, LocalDateTime inizio, Integer tempo) {

        LocalDateTime fine;

        if(timestamp!=null) {
            fine=inizio.plusMinutes(tempo);
        }

        else {
            fine=null;
        }

        return fine;
    }

    @Override
    public boolean existsByNome(String nome, Integer utenteId) {

        var exists=false;
        List<Lavatrice> lavatrici= readAll(utenteId);

        try {
            var connection = database.getConnection();

            for(Dispositivo lavatrice:lavatrici) {
                try (var resultSet = connection.createStatement().executeQuery(
                        "SELECT * FROM lavatrici WHERE nome='"+nome+"' AND id='"+lavatrice.getId()+"'")) {

                    exists=resultSet.next();
                }
            }


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return exists;

    }

    @Override
    public Integer create(Lavatrice lavatrice, Integer utenteId) {

        Integer id=null;

        Timestamp timestamp;
        if(lavatrice.getInizio()!=null) {
            timestamp=new Timestamp((lavatrice.getInizio().toInstant(ZoneOffset.UTC).toEpochMilli()));
        }

        else {
            timestamp=null;
        }

        try {
            var connection = database.getConnection();

            try (PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO lavatrici(nome, acceso, temperatura, tempo, inizio " +
                            ") VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setString(1, lavatrice.getNome());
                pstmt.setBoolean(2, lavatrice.getAcceso());
                pstmt.setInt(3, lavatrice.getTemperatura());
                pstmt.setInt(4, lavatrice.getTempo());
                pstmt.setTimestamp(5,timestamp);

                int affectedRows = pstmt.executeUpdate();

                if(affectedRows > 0) {
                    id=setId(pstmt);
                }

                createAssegnamento(id,utenteId);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return id;
    }

    private static Integer setId(PreparedStatement pstmt) throws SQLException {

        Integer id = 0;

        try(ResultSet rs = pstmt.getGeneratedKeys()) {
            if(rs.next()) {
                id = rs.getInt(1);
            }
        }

        return id;
    }

    @Override
    public void deleteById(Integer lavatriceId, Integer utenteId) {

        try {
            var connection = database.getConnection();

            try (PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM assegnamento_lavatrici WHERE utente_id=? AND lavatrice_id=?")) {

                pstmt.setInt(1, utenteId);
                pstmt.setInt(2, lavatriceId);

                int deletedRows = pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public boolean existsById(Integer id) {

        var exists=false;

        try {
            var connection = database.getConnection();

            try (var resultSet = connection.createStatement().executeQuery(
                    "SELECT * FROM lavatrici WHERE id='"+id+"'")) {

                exists=resultSet.next();
            }


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return exists;
    }

    @Override
    public void update(Lavatrice lavatrice) {

        Timestamp timestamp;
        if(lavatrice.getInizio()!=null) {
            timestamp=new Timestamp((lavatrice.getInizio().toInstant(ZoneOffset.UTC).toEpochMilli()));
        }

        else {
            timestamp=null;
        }

        try {
            var connection = database.getConnection();

            try (PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE lavatrici "
                            + "SET nome = ?, acceso= ?, temperatura= ?, tempo= ?, inizio= ? "
                            + "WHERE id = ?")) {

                pstmt.setString(1, lavatrice.getNome());
                pstmt.setBoolean(2, lavatrice.getAcceso());
                pstmt.setInt(3, lavatrice.getTemperatura());
                pstmt.setInt(4, lavatrice.getTempo());
                pstmt.setTimestamp(5,timestamp);
                pstmt.setInt(6, lavatrice.getId());

                int affectedRows = pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void createAssegnamento(Integer lavatriceId, Integer utenteId) {

        try {
            var connection = database.getConnection();

            try (PreparedStatement pstmt2 = connection.prepareStatement(
                    "INSERT INTO assegnamento_lavatrici(utente_id, lavatrice_id) VALUES(?,?)",
                    Statement.NO_GENERATED_KEYS)) {
                pstmt2.setInt(1, utenteId);
                pstmt2.setInt(2, lavatriceId);

                int affectedRows2 = pstmt2.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
