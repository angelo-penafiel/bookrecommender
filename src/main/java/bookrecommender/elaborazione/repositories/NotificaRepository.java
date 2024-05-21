
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.repositories;

import bookrecommender.elaborazione.entities.Notifica;

import java.util.List;

public interface NotificaRepository {

    List<Notifica> getAll(Integer utenteId);

    Integer add(Notifica notifica, Integer utenteId);

}
