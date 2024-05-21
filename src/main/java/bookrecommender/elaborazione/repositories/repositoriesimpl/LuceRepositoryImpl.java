
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.repositories.repositoriesimpl;

import bookrecommender.elaborazione.entities.Luce;
import bookrecommender.elaborazione.dao.daoimpl.LuceDaoImpl;
import bookrecommender.elaborazione.repositories.LuceRepository;

import java.util.List;

public class LuceRepositoryImpl implements LuceRepository {

    private final LuceDaoImpl luceDaoImpl;

    public LuceRepositoryImpl() {
        luceDaoImpl=new LuceDaoImpl();
    }

    @Override
    public List<Luce> getAll(Integer utenteId) {
        return luceDaoImpl.readAll(utenteId);
    }

    @Override
    public List<Integer> getIdAssegnamenti(Integer utenteId) {
        return luceDaoImpl.readIdAssegnamenti(utenteId);
    }

    @Override
    public Luce get(Integer id) {
        return luceDaoImpl.read(id);
    }

    @Override
    public boolean existsByNome(String nome, Integer utenteId) {
        return luceDaoImpl.existsByNome(nome,utenteId);
    }

    @Override
    public Integer add(Luce luce, Integer utenteId) {
        return luceDaoImpl.create(luce,utenteId);
    }

    @Override
    public void removeById(Integer luceId, Integer utenteId) {
        luceDaoImpl.deleteById(luceId,utenteId);
    }

    @Override
    public void update(Luce luce) {
        luceDaoImpl.update(luce);
    }

    @Override
    public boolean existsById(Integer id) {
        return luceDaoImpl.existsById(id);
    }

    @Override
    public void addAssegnamento(Integer luceId, Integer utenteId) {
        luceDaoImpl.createAssegnamento(luceId,utenteId);
    }

}
