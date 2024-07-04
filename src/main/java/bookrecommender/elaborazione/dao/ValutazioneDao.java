package bookrecommender.elaborazione.dao;

import bookrecommender.elaborazione.entities.Valutazione;
import java.io.IOException;
import java.util.List;

/**
 * Interfaccia che implementa il design pattern DAO.
 * La sua funzione è quella di separare la logica
 * di accesso ai dati dalla logica di business.
 * Ha lo scopo di prelevare i dati del file csv
 * (ValutazioniLibri.dati.csv) e creare oggetti o
 * campi di classe entities/Valutazione.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public interface ValutazioneDao {

  /**
   * Restituisce una lista di oggetti
   * Valutazione che hanno come libroId
   * quello dato.
   *
   * @param libroId indica il libroId
   *
   * @return lista di oggetti Valutazione
   */

  List<Valutazione> getByLibroId(String libroId) throws IOException;

}
