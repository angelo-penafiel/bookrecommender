package bookrecommender.elaborazione.dao;

import bookrecommender.elaborazione.entities.Libreria;
import bookrecommender.elaborazione.entities.Libro;
import java.io.IOException;
import java.util.List;

/**
 * Interfaccia che implementa il design pattern DAO.
 * La sua funzione è quella di separare la logica
 * di accesso ai dati dalla logica di business.
 * Ha lo scopo di prelevare i dati dai file csv e
 * creare oggetti o campi di classe Libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public interface LibreriaDao {

    //METODI

    /**
     * Restituisce un valore boolean che indica la
     * presenza di una libreria con il nome dato di
     * un utente.
     *
     * @param nome indica il nome
     *
     * @param userId indica lo userId
     *
     * @return se esiste la libreria
     */

    boolean existsByNome(String nome, String userId) throws IOException;

    /**
     * Aggiunge una libreria dati un nome e lo
     * userId.
     *
     * @param nome indica il nome
     *
     * @param userId indica lo userId
     *
     * @return la libreria aggiunta
     */

    Libreria add(String nome, String userId) throws IOException;

    /**
     * Restituisce una lista di id delle librerie
     * che hanno come userId quello dato.
     *
     * @param userId indica lo userId
     *
     * @return la lista di id
     */

    List<Integer> getIdsByUserId( String userId) throws IOException;

    /**
     * Restituisce una lista di nomi delle
     * librerie che hanno come id quelli dati.
     *
     * @param ids indica gli id
     *
     * @return la lista di nomi
     */

    List<String> getNomiByIds(List<Integer> ids) throws IOException;

    /**
     * Restituisce un oggetto libreria correlato
     * all'id dato.
     *
     * @param id indica l'id
     *
     * @return la libreria
     */

    Libreria getById(Integer id) throws IOException;

    /**
     * Restituisce una lista di id dei libri correlati
     * all'id della libreria dato.
     *
     * @param id indica l'id della libreria
     *
     * @return lista dei libri
     */

    List<Integer> getIdsLibriByIdLibreria(Integer id) throws IOException;

    /**
     * Restituisce un valore boolean che indica se un
     * libro è già presnte nella libreria.
     *
     * @param libreria indica la libreria
     *
     * @param libro indica il libro
     *
     * @return se un libro è presente
     */

    boolean existsLibro(Libreria libreria, Libro libro) throws IOException;

    /**
     * Aggiunge un libro nella libreria data.
     *
     * @param libreria indica la libreriaù
     *
     * @param libro indica il libro
     */

    void addLibro(Libreria libreria, Libro libro) throws IOException;

}
