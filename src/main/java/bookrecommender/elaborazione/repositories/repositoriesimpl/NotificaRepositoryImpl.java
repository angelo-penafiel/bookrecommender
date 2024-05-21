
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.repositories.repositoriesimpl;

import bookrecommender.elaborazione.entities.Notifica;
import bookrecommender.elaborazione.dao.daoimpl.NotificaDaoImpl;
import bookrecommender.elaborazione.repositories.NotificaRepository;

import java.util.List;

public class NotificaRepositoryImpl implements NotificaRepository {

    private final NotificaDaoImpl notificaDaoImpl;

    public NotificaRepositoryImpl() {
        notificaDaoImpl=new NotificaDaoImpl();
    }

    @Override
    public List<Notifica> getAll(Integer utenteId) {
        return notificaDaoImpl.readAll(utenteId);
    }

    @Override
    public Integer add(Notifica notifica, Integer utenteId) {
        return notificaDaoImpl.create(notifica,utenteId);
    }
}
