package bookrecommender.elaborazione.dao;

import bookrecommender.elaborazione.entities.Consigliati;
import bookrecommender.elaborazione.entities.Libro;
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
 * @author Angelo Penafiel, Lorenzo
 * @version 1.0
 */

public interface ConsigliatiDao {

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

    List<Consigliati> getAll() throws IOException;

    Consigliati getByUserId(String userId) throws IOException;

}
