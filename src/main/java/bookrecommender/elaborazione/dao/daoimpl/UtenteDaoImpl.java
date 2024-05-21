
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.dao.daoimpl;

import bookrecommender.elaborazione.dao.UtenteDao;
import bookrecommender.elaborazione.database.Database;
import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDaoImpl implements UtenteDao<Utente> {

    private static String parteQuery = "SELECT * FROM utenti WHERE username='";

    private final Database database;

    public UtenteDaoImpl() {

        database=Database.getInstance();
    }

    @Override
    public Utente readByUsernamePassword(String username, String password) {

        Utente utente;

        try {
            var connection = database.getConnection();

            try (var resultSet = connection.createStatement().executeQuery(
                    parteQuery+username+"' AND " + "password='"+password+"'")) {

                if(resultSet.next()) {
                    utente= new Utente(resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"));
                }

                else {
                    utente=null;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return utente;
    }

    @Override
    public boolean existsByUsername(String username) {

        boolean exists;

        try {
            var connection = database.getConnection();

            try (var resultSet = connection.createStatement().executeQuery(
                    parteQuery+username+"'")) {

                exists=resultSet.next();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return exists;

    }

    @Override
    public Integer create(Utente utente) {

        Integer id=null;

        try {
            var connection = database.getConnection();

            try (var pstmt = connection.prepareStatement(
                    "INSERT INTO utenti(username, password) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setString(1, utente.getUsername());
                pstmt.setString(2, utente.getPassword());

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

    @Override
    public Integer readIdByUsername(String username) {

        Integer id=null;

        try {
            var connection = database.getConnection();

            try (var resultSet = connection.createStatement().executeQuery(
                    parteQuery+username+"'")) {

                if(resultSet.next()) {
                    id=resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return id;
    }

    @Override
    public List<Utente> readAllAssegnamentiLuci(Integer luceId) {

        List<Integer> utentiId= readIdAssegnamentiLuci(luceId);
        List<Utente> utenti=new ArrayList<>();

        if(!utentiId.isEmpty()) {
            for(Integer utenteId:utentiId) {
                utenti.add(read(utenteId));
            }
        }

        return utenti;
    }

    public List<Integer> readIdAssegnamentiLuci(Integer luceId) {

        List<Integer> utentiId=new ArrayList<>();

        try {
            var connection = database.getConnection();

            try (var resultSet = connection.createStatement().executeQuery(
                    "SELECT * FROM assegnamento_luci WHERE luce_id='"+luceId+"'")) {

                while (resultSet.next()) {
                    utentiId.add(resultSet.getInt("utente_id"));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return utentiId;
    }

    @Override
    public List<Utente> readAllAssegnamentiLavatrici(Integer lavatriceId) {

        List<Integer> utentiId= readIdAssegnamentiLavatrici(lavatriceId);
        List<Utente> utenti=new ArrayList<>();

        if(!utentiId.isEmpty()) {
            for(Integer utenteId:utentiId) {
                utenti.add(read(utenteId));
            }
        }

        return utenti;
    }

    public List<Integer> readIdAssegnamentiLavatrici(Integer lavatriceId) {

        List<Integer> utentiId=new ArrayList<>();

        try {
            var connection = database.getConnection();

            try (var resultSet = connection.createStatement().executeQuery(
                    "SELECT * FROM assegnamento_lavatrici WHERE lavatrice_id='"+lavatriceId+"'")) {

                while (resultSet.next()) {
                    utentiId.add(resultSet.getInt("utente_id"));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return utentiId;
    }

    @Override
    public Utente read(Integer id) {

        Utente utente=null;

        try {
            var connection = database.getConnection();

                try (var resultSet2 = connection.createStatement().executeQuery(
                        "SELECT * FROM utenti WHERE id='"+ id +"' ORDER BY id")) {
                    while (resultSet2.next()) {
                        utente=new Utente(resultSet2.getInt("id"),
                                resultSet2.getString("username"),
                                resultSet2.getString("password"));
                    }
                }


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

        return utente;
    }

}
