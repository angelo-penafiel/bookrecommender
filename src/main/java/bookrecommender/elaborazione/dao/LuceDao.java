
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.dao;

import java.util.List;

public interface LuceDao<T> {

    List<T> readAll(Integer utenteId);

    List<Integer> readIdAssegnamenti(Integer utenteId);

    T read(Integer id);

    boolean existsByNome(String nome, Integer utenteId);

    Integer create(T luce, Integer utenteId);

    void deleteById(Integer luceId, Integer utenteId);

    void update(T luce);

    boolean existsById(Integer id);

    void createAssegnamento(Integer luceId, Integer utenteId);

}
