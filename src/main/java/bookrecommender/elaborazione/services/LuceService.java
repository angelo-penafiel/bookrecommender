
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.services;

import bookrecommender.elaborazione.entities.Luce;
import bookrecommender.elaborazione.repositories.LuceRepository;
import bookrecommender.elaborazione.repositories.repositoriesimpl.LuceRepositoryImpl;

import java.util.List;

public class LuceService {

    private final LuceRepository luceRepository;

    public LuceService() {
        luceRepository=new LuceRepositoryImpl();
    }

    public List<Luce> getAll(Integer utenteId) {
        return luceRepository.getAll(utenteId);
    }

    public List<Integer> getIdAssegnamenti(Integer utenteId) {
        return luceRepository.getIdAssegnamenti(utenteId);
    }

    public Luce get(Integer id) {
        return luceRepository.get(id);
    }

    public boolean existsByNome(String nome, Integer utenteId) {
        return luceRepository.existsByNome(nome,utenteId);
    }

    public Integer add(Luce luce, Integer utenteId) {
        return luceRepository.add(luce,utenteId);
    }

    public void removeById(Integer luceId, Integer utenteId) {
        luceRepository.removeById(luceId,utenteId);
    }

    public void update(Luce luce) {
        luceRepository.update(luce);
    }

    public boolean existsById(Integer id) {
        return luceRepository.existsById(id);
    }

    public void addAssegnamento(Integer luceId, Integer utenteId) {
        luceRepository.addAssegnamento(luceId,utenteId);
    }

}
