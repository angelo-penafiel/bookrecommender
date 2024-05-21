
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.repositories;

import bookrecommender.elaborazione.entities.Utente;

import java.util.List;

public interface UtenteRepository {

    Utente getByUsernamePassword(String username, String password);

    boolean existsByUsername(String username);

    Integer add(Utente utente);

    Integer getIdByUsername(String username);

    List<Utente> getAllAssegnamentiLuci(Integer luceId);

    List<Integer> getIdAssegnamentiLuci(Integer luceId);

    List<Utente> getAllAssegnamentiLavatrici(Integer lavatriceId);

    List<Integer> getIdAssegnamentiLavatrici(Integer lavatriceId);

    Utente get(Integer id);

}
