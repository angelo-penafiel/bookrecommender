
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.dao;

import java.util.List;

public interface NotificaDao<T> {

    List<T> readAll(Integer utenteId);

    Integer create(T notifica, Integer utenteId);

}
