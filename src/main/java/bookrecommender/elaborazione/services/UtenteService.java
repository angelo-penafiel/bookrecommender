
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.services;

import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.repositories.UtenteRepository;
import bookrecommender.elaborazione.repositories.repositoriesimpl.UtenteRepositoryImpl;

import java.util.List;

public class UtenteService {

    private final UtenteRepository utenteRepository;

    public UtenteService() {
        utenteRepository=new UtenteRepositoryImpl();
    }

    public Utente getByUsernamePassword(String username, String password) {
        return utenteRepository.getByUsernamePassword(username,password);
    }

    public boolean existsByUsername(String username) {
        return utenteRepository.existsByUsername(username);
    }

    public Integer add(Utente utente) {
        return utenteRepository.add(utente);
    }

    public Integer getIdByUsername(String username) {
        return utenteRepository.getIdByUsername(username);
    }

    public List<Utente> getAllAssegnamentiLuci(Integer luceId) {
        return utenteRepository.getAllAssegnamentiLuci(luceId);
    }

    public List<Integer> getIdAssegnamentiLuci(Integer luceId) {
        return utenteRepository.getIdAssegnamentiLuci(luceId);
    }

    public List<Utente> getAllAssegnamentiLavatrici(Integer lavatriceId) {
        return utenteRepository.getAllAssegnamentiLavatrici(lavatriceId);
    }

    public List<Integer> getIdAssegnamentiLavatrici(Integer lavatriceId) {
        return utenteRepository.getIdAssegnamentiLavatrici(lavatriceId);
    }

    public Utente get(Integer id) {
        return utenteRepository.get(id);
    }
}
