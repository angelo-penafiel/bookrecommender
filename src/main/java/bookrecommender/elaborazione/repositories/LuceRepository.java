
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.repositories;

import bookrecommender.elaborazione.entities.Luce;

import java.util.List;

public interface LuceRepository {

    List<Luce> getAll(Integer utenteId);

    List<Integer> getIdAssegnamenti(Integer utenteId);

    Luce get(Integer id);

    boolean existsByNome(String nome, Integer utenteId);

    Integer add(Luce luce, Integer utenteId);

    void removeById(Integer luceId, Integer utenteId);

    void update(Luce luce);

    boolean existsById(Integer id);

    void addAssegnamento(Integer luceId, Integer utenteId);

}
