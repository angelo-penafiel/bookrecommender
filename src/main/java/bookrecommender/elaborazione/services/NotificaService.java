
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.services;

import bookrecommender.elaborazione.entities.Notifica;
import bookrecommender.elaborazione.repositories.NotificaRepository;
import bookrecommender.elaborazione.repositories.repositoriesimpl.NotificaRepositoryImpl;

import java.util.List;

public class NotificaService {

    private final NotificaRepository notificaRepository;

    public NotificaService() {
        notificaRepository=new NotificaRepositoryImpl();
    }

    public List<Notifica> getAll(Integer utenteId) {
        return notificaRepository.getAll(utenteId);
    }

    public Integer add(Notifica notifica, Integer utenteId) {
        return notificaRepository.add(notifica,utenteId);
    }
}
