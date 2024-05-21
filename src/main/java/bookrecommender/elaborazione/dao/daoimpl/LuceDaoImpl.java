
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.dao.daoimpl;

import bookrecommender.elaborazione.database.Database;
import bookrecommender.elaborazione.entities.Luce;
import bookrecommender.elaborazione.exceptions.DatabaseException;
import bookrecommender.elaborazione.factory.Dispositivo;
import bookrecommender.elaborazione.factory.DispositivoEnum;
import bookrecommender.elaborazione.factory.DispositivoFactory;
import bookrecommender.elaborazione.dao.LuceDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LuceDaoImpl implements LuceDao<Luce> {

    private final Database database;

    public LuceDaoImpl() {
        database=Database.getInstance();
    }

    @Override
    public List<Luce> readAll(Integer utenteId) {

        List<Luce> luci=new ArrayList<>();
        List<Integer> luciId= readIdAssegnamenti(utenteId);

        if(!luciId.isEmpty()) {
            for (Integer luceId : luciId) {
                luci.add(read(luceId));
            }
        }

        return luci;
    }

    public List<Integer> readIdAssegnamenti(Integer utenteId) {

        List<Integer> luciId=new ArrayList<>();

        try {
            var connection = database.getConnection();

            try (var resultSet = connection.createStatement().executeQuery(
                    "SELECT * FROM assegnamento_luci WHERE utente_id='"+utenteId+"' ORDER BY luce_id")) {

                while (resultSet.next()) {
                    luciId.add(resultSet.getInt("luce_id"));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return luciId;

    }

    public Luce read(Integer id) {

        Dispositivo dispositivo=null;

        try {
            var connection = database.getConnection();

            try (var resultSet2 = connection.createStatement().executeQuery(
                    "SELECT * FROM luci WHERE id='" + id + "' ORDER BY id")) {
                while (resultSet2.next()) {

                    List<Object> parametri = new ArrayList<>();
                    parametri.add(resultSet2.getInt("id"));
                    parametri.add(resultSet2.getString("nome"));
                    parametri.add(resultSet2.getBoolean("acceso"));

                    dispositivo = DispositivoFactory.getDispositivo(
                            DispositivoEnum.LUCE_CON_ID, parametri);
                }
            }


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return (Luce) dispositivo;
    }

    @Override
    public boolean existsByNome(String nome, Integer utenteId) {

        var exists=false;
        List<Luce> luci= readAll(utenteId);

        try {
            var connection = database.getConnection();

            for(Dispositivo luce:luci) {
                try (var resultSet = connection.createStatement().executeQuery(
                        "SELECT * FROM luci WHERE nome='"+nome+"' AND id='"+luce.getId()+"'")) {

                    exists=resultSet.next();
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return exists;
    }

    @Override
    public Integer create(Luce luce, Integer utenteId) {

        Integer id=null;

        try {
            var connection = database.getConnection();

            try (PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO luci(nome, acceso) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setString(1, luce.getNome());
                pstmt.setBoolean(2, luce.getAcceso());

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

        Integer id=0;

        try(ResultSet rs = pstmt.getGeneratedKeys()) {
            if(rs.next()) {
                id = rs.getInt(1);
            }
        }

        return id;
    }

    @Override
    public void deleteById(Integer luceId, Integer utenteId) {

        try {
            var connection = database.getConnection();

            try (PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM assegnamento_luci WHERE utente_id=? AND luce_id=?")) {

                pstmt.setInt(1, utenteId);
                pstmt.setInt(2, luceId);

                int deletedRows = pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(Luce luce) {

        try {
            var connection = database.getConnection();

            try (PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE luci "
                            + "SET nome = ?, acceso= ? "
                            + "WHERE id = ?")) {

                pstmt.setString(1, luce.getNome());
                pstmt.setBoolean(2, luce.getAcceso());
                pstmt.setInt(3, luce.getId());

                int affectedRows = pstmt.executeUpdate();
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
                    "SELECT * FROM luci WHERE id='"+id+"'")) {

                exists=resultSet.next();
            }


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return exists;
    }

    @Override
    public void createAssegnamento(Integer luceId, Integer utenteId) {

        try {
            var connection = database.getConnection();

            try (PreparedStatement pstmt2 = connection.prepareStatement(
                    "INSERT INTO assegnamento_luci(utente_id, luce_id) VALUES(?,?)",
                    Statement.NO_GENERATED_KEYS)) {
                pstmt2.setInt(1, utenteId);
                pstmt2.setInt(2, luceId);

                int affectedRows2 = pstmt2.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
