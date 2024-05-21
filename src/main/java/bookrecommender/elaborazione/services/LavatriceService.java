
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.services;

import bookrecommender.elaborazione.entities.Lavatrice;
import bookrecommender.elaborazione.repositories.LavatriceRepository;
import bookrecommender.elaborazione.repositories.repositoriesimpl.LavatriceRepositoryImpl;

import java.util.List;

public class LavatriceService {

    private final LavatriceRepository lavatriceRepository;

    public LavatriceService() {
        lavatriceRepository =new LavatriceRepositoryImpl();
    }

    public List<Lavatrice> getAll(Integer utenteId) {
        return lavatriceRepository.getAll(utenteId);
    }

    public List<Integer> getIdAssegnamenti(Integer utenteId) {
        return lavatriceRepository.getIdAssegnamenti(utenteId);
    }

    public Lavatrice get(Integer id) {
        return lavatriceRepository.get(id);
    }

    public boolean existsByNome(String nome, Integer utenteId) {
        return lavatriceRepository.existsByNome(nome,utenteId);
    }

    public Integer add(Lavatrice lavatrice, Integer utenteId) {
        return lavatriceRepository.add(lavatrice,utenteId);
    }

    public void removeById(Integer lavatriceId, Integer utenteId) {
        lavatriceRepository.removeById(lavatriceId,utenteId);
    }

    public void update(Lavatrice lavatrice) {
        lavatriceRepository.update(lavatrice);
    }

    public boolean existsById(Integer id) {
        return lavatriceRepository.existsById(id);
    }

    public void addAssegnamento(Integer lavatriceId, Integer utenteId) {
        lavatriceRepository.addAssegnamento(lavatriceId,utenteId);
    }

}
