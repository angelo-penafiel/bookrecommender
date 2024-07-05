package bookrecommender.elaborazione.dao;

import bookrecommender.elaborazione.entities.Libreria;
import bookrecommender.elaborazione.entities.Libro;
import java.io.IOException;
import java.util.List;

/**
 * Interfaccia che implementa il design pattern DAO.
 * La sua funzione è quella di separare la logica
 * di accesso ai dati dalla logica di business.
 * Ha lo scopo di prelevare e aggiugere i dati
 * dei file csv (Librerie.dati.csv e
 * AssegnamentoLibrerieLibri.dati.csv) e creare
 * oggetti o campi di classe Libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public interface LibreriaDao {

    //METODI

    /**
     * Restituisce un valore boolean che indica la
     * presenza di una libreria con il nome dato di
     * un utente dato.
     *
     * @param nome indica il nome
     *
     * @param userId indica lo userId
     *
     * @return se esiste la libreria
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    boolean existsByNome(String nome, String userId) throws IOException;

    /**
     * Aggiunge al file Librerie.dati.csv una
     * libreria dati un nome e lo userId.
     *
     * @param nome indica il nome
     *
     * @param userId indica lo userId
     *
     * @return la libreria aggiunta
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    Libreria add(String nome, String userId) throws IOException;

    /**
     * Restituisce una lista di id delle librerie
     * che hanno come userId quello dato.
     *
     * @param userId indica lo userId
     *
     * @return la lista di id
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    List<Integer> getIdsByUserId(String userId) throws IOException;

    /**
     * Restituisce una lista di nomi delle
     * librerie che hanno come id quelli dati.
     *
     * @param ids indica gli id
     *
     * @return la lista di nomi
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    List<String> getNomiByIds(List<Integer> ids) throws IOException;

    /**
     * Restituisce un oggetto libreria correlato
     * all'id dato.
     *
     * @param id indica l'id
     *
     * @return la libreria
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    Libreria getById(Integer id) throws IOException;

    /**
     * Restituisce una lista di id dei libri correlati
     * all'id dato della libreria.
     *
     * @param id indica l'id della libreria
     *
     * @return lista dei libri
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
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
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    boolean existsLibro(Libreria libreria, Libro libro) throws IOException;

    /**
     * Aggiunge un libro nella libreria data
     * al file AssegnamentoLibrerieLibri.dati.csv.
     *
     * @param libreria indica la libreriaù
     *
     * @param libro indica il libro
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    void addLibro(Libreria libreria, Libro libro) throws IOException;

}
