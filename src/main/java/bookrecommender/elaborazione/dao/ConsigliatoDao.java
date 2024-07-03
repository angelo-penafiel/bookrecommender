package bookrecommender.elaborazione.dao;

import bookrecommender.elaborazione.entities.Consigliato;
import bookrecommender.elaborazione.entities.Libro;
import java.io.IOException;
import java.util.List;

/**
 * Interfaccia che implementa il design pattern DAO.
 * La sua funzione è quella di separare la logica
 * di accesso ai dati dalla logica di business.
 * Ha lo scopo di prelevare i dati dai file csv e
 * creare oggetti o campi di classe Libro.
 *
 * @author Angelo Penafiel, Lorenzo
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

    void add(String userId) throws IOException;

    void addLibro(String userId, Libro libro) throws IOException;

    List<Consigliato> getAll() throws IOException;

    Consigliato getByUserId(String userId) throws IOException;

    List<Consigliato> getByLibroId(String libroId) throws IOException;

}
