
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.dao;

import java.util.List;

public interface UtenteDao<T> {

    T readByUsernamePassword(String username, String password);

    boolean existsByUsername(String username);

    Integer create(T utente);

    Integer readIdByUsername(String username);

    List<T> readAllAssegnamentiLuci(Integer luceId);

    List<Integer> readIdAssegnamentiLuci(Integer luceId);

    List<T> readAllAssegnamentiLavatrici(Integer lavatriceId);

    List<Integer> readIdAssegnamentiLavatrici(Integer lavatriceId);

    T read(Integer id);

}
