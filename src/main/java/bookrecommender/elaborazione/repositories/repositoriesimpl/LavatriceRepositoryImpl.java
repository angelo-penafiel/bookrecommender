
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.repositories.repositoriesimpl;

import bookrecommender.elaborazione.dao.daoimpl.LavatriceDaoImpl;
import bookrecommender.elaborazione.entities.Lavatrice;
import bookrecommender.elaborazione.repositories.LavatriceRepository;

import java.util.List;

public class LavatriceRepositoryImpl implements LavatriceRepository {

    private final LavatriceDaoImpl lavatriceDaoImpl;

    public LavatriceRepositoryImpl() {
        lavatriceDaoImpl=new LavatriceDaoImpl();
    }

    @Override
    public List<Lavatrice> getAll(Integer utenteId) {
        return lavatriceDaoImpl.readAll(utenteId);
    }

    @Override
    public List<Integer> getIdAssegnamenti(Integer utenteId) {
        return lavatriceDaoImpl.readIdAssegnamenti(utenteId);
    }

    @Override
    public Lavatrice get(Integer id) {
        return lavatriceDaoImpl.read(id);
    }

    @Override
    public boolean existsByNome(String nome, Integer utenteId) {
        return lavatriceDaoImpl.existsByNome(nome,utenteId);
    }

    @Override
    public Integer add(Lavatrice lavatrice, Integer utenteId) {
        return lavatriceDaoImpl.create(lavatrice,utenteId);
    }

    @Override
    public void removeById(Integer lavatriceId, Integer utenteId) {
        lavatriceDaoImpl.deleteById(lavatriceId,utenteId);
    }

    @Override
    public void update(Lavatrice lavatrice) {
        lavatriceDaoImpl.update(lavatrice);
    }

    @Override
    public boolean existsById(Integer id) {
        return lavatriceDaoImpl.existsById(id);
    }

    @Override
    public void addAssegnamento(Integer lavatriceId, Integer utenteId) {
        lavatriceDaoImpl.createAssegnamento(lavatriceId,utenteId);
    }

}
