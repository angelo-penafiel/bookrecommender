package bookrecommender.elaborazione.dao;

import bookrecommender.elaborazione.entities.Consigliato;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Interfaccia che implementa il design pattern DAO.
 * La sua funzione è quella di separare la logica
 * di accesso ai dati dalla logica di business.
 * Ha lo scopo di prelevare i dati dai file csv e
 * creare oggetti o campi di classe Libro.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public interface ConsigliatoDao {

    //METODI

    /**
     * Restituisce una lista di id dei libri correlati
     * dato il titolo del libro cercato.
     *
     * @param
     *
     * @return lista di id correlati al titolo
     */

    Consigliato getByUserIdAndLibroId(String userId, String  libroId) throws IOException;

    List<String>getLibriConsigliatiById (String id) throws IOException;

    void add(String userId, String  libroId) throws IOException;

    void addLibroConsigliato(String id, String  libroId) throws IOException;

    HashMap<String, Integer> getLibriConsigliatiCountedByLibroId(String libroId) throws IOException;

    List<Consigliato> getAllByLibroId(String  libroId) throws IOException;
}
