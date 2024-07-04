package bookrecommender.elaborazione.dao;

import bookrecommender.elaborazione.entities.Consigliato;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Interfaccia che implementa il design pattern DAO.
 * La sua funzione è quella di separare la logica
 * di accesso ai dati dalla logica di business.
 * Ha lo scopo di prelevare e aggiugere i dati
 * dei file csv (ConsigliLibri.dati.csv e
 * AssegnamentoConsigliatiLibri.dati.csv) e creare
 * oggetti o campi di classe Consigliato.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public interface ConsigliatoDao {

    //METODI

    /**
     * Restituisce un oggetto Consigliato dato
     * lo userId e l'id del libro corrente.
     *
     * @param userId indica lo userId
     *
     * @param libroId indica l'id del libro
     *                corrente
     *
     * @return oggetto Consigliato
     */

    Consigliato getByUserIdAndLibroId(String userId, String  libroId) throws IOException;

    /**
     * Restituisce una lista di id di libri
     * consigliati dato l'id dell'oggetto
     * Consigliato.
     *
     * @param id indica l'id dell'oggetto
     *           Consigliato
     *
     * @return lista di id di libri consigliati
     */

    List<String> getLibriConsigliatiById (String id) throws IOException;

    /**
     * Aggiunge lo userId e l'id del libro
     * corrente dati al file ConsigliLibri.dati.csv.
     *
     * @param userId indica lo userId
     *
     * @param libroId indica l'id del libro corrente
     */

    void add(String userId, String  libroId) throws IOException;

    /**
     * Aggiunge l'id dell'oggetto Consigliato
     * e l'id del libro consigliato dati al file
     * AssegnamentoConsigliatiLibri.dati.csv.
     *
     * @param id indica l'id dell'oggetto Consigliato
     *
     * @param libroConsigliatoId indica l'id del
     *                           libro consigliato
     */

    void addLibroConsigliato(String id, String  libroConsigliatoId) throws IOException;

    /**
     * Restituisce un HashMap, contente come
     * chiave l'id del libro consigliato e come
     * valore il numero di volte che è stato
     * consigliato dagli utenti, dato l'id del
     * libro corrente.
     *
     * @param libroId l'id del libro corrente
     *
     * @return HashMap contente come chiave l'id
     * del libro consigliato e come valore il
     * numero di volte che è stato consigliato
     * dagli utenti
     */

    HashMap<String, Integer> getLibriConsigliatiCountedByLibroId(String libroId) throws IOException;

    /**
     * Restituisce una lista di oggetti
     * Consigliato dato l'id del libro corrente.
     *
     * @param libroId indica l'id del libro
     *                corrente
     *
     * @return lista di oggetti Consigliato
     */

    List<Consigliato> getAllByLibroId(String libroId) throws IOException;
}
