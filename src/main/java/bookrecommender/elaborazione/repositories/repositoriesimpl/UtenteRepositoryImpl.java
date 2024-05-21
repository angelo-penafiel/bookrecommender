
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.repositories.repositoriesimpl;

import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.dao.daoimpl.UtenteDaoImpl;
import bookrecommender.elaborazione.repositories.UtenteRepository;

import java.util.List;

public class UtenteRepositoryImpl implements UtenteRepository {

    private final UtenteDaoImpl utenteDaoImpl;

    public UtenteRepositoryImpl() {
        utenteDaoImpl=new UtenteDaoImpl();
    }

    @Override
    public Utente getByUsernamePassword(String username, String password) {
        return utenteDaoImpl.readByUsernamePassword(username,password);
    }

    @Override
    public boolean existsByUsername(String username) {
        return utenteDaoImpl.existsByUsername(username);
    }

    @Override
    public Integer add(Utente utente) {
        return utenteDaoImpl.create(utente);
    }

    @Override
    public Integer getIdByUsername(String username) {
        return utenteDaoImpl.readIdByUsername(username);
    }

    @Override
    public List<Utente> getAllAssegnamentiLuci(Integer luceId) {
        return utenteDaoImpl.readAllAssegnamentiLuci(luceId);
    }

    @Override
    public List<Integer> getIdAssegnamentiLuci(Integer luceId) {
        return utenteDaoImpl.readIdAssegnamentiLuci(luceId);
    }

    @Override
    public List<Utente> getAllAssegnamentiLavatrici(Integer lavatriceId) {
        return utenteDaoImpl.readAllAssegnamentiLavatrici(lavatriceId);
    }

    @Override
    public List<Integer> getIdAssegnamentiLavatrici(Integer lavatriceId) {
        return utenteDaoImpl.readIdAssegnamentiLavatrici(lavatriceId);
    }

    @Override
    public Utente get(Integer id) {
        return utenteDaoImpl.read(id);
    }
}
