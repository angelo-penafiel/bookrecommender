
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.dao;

import java.util.List;

public interface LavatriceDao<T> {

    List<T> readAll(Integer utenteId);

    List<Integer> readIdAssegnamenti(Integer utenteId);

    T read(Integer id);

    boolean existsByNome(String nome, Integer utenteId);

    Integer create(T lavatrice, Integer utenteId);

    void deleteById(Integer lavatriceId, Integer utenteId);

    void update(T lavatrice);

    boolean existsById(Integer id);

    void createAssegnamento(Integer lavatriceId, Integer utenteId);

}
