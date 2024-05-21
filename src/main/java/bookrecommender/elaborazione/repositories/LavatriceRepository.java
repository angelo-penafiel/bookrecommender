
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.repositories;

import bookrecommender.elaborazione.entities.Lavatrice;

import java.util.List;

public interface LavatriceRepository {

    List<Lavatrice> getAll(Integer utenteId);

    List<Integer> getIdAssegnamenti(Integer utenteId);

    Lavatrice get(Integer id);

    boolean existsByNome(String nome, Integer utenteId);

    Integer add(Lavatrice lavatrice, Integer utenteId);

    void removeById(Integer lavatriceId, Integer utenteId);

    void update(Lavatrice lavatrice);

    boolean existsById(Integer id);

    void addAssegnamento(Integer lavatriceId, Integer utenteId);

}
