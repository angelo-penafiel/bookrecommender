package bookrecommender.struttura.libreria;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.dao.LibroDao;
import bookrecommender.elaborazione.dao.daoimpl.LibreriaDaoImpl;
import bookrecommender.elaborazione.dao.daoimpl.LibroDaoImpl;
import bookrecommender.elaborazione.entities.Libreria;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.libreria.SelezioneLibroMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibroTitolo;
import java.io.IOException;
import java.util.List;

/**
 * Classe che ha la funzione di gestire la
 * sezione di selezione del libro della libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class SelezioneLibro {

  //CAMPI

  /**
   * Campo che indica il libro della libreria
   * selezionato dall'utente.
   */

  private Libro libro;

  /**
   * Campo che indica se tornare indietro
   */

  private boolean tornaIndietro;

  //COSTRUTTORE

  /**
   * Restituisce l'oggetto di tipo SelezioneLibro
   * e all'interno viene effettuata la stampa
   * dei libri della libreria data e l'inserimento
   * della scelta del libro da selezionare.
   *
   * @param libreria indica la libreria
   */

  public SelezioneLibro(Libreria libreria) {

    List<Integer> idsLibriTrovati;
    LibreriaDao libreriaDao=new LibreriaDaoImpl();
    try {
      idsLibriTrovati=libreriaDao.getIdsLibriByIdLibreria(libreria.getId());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    if(!idsLibriTrovati.isEmpty()) {

      tornaIndietro=false;

      Integer libroSelezionato = RicercaLibroTitolo.selezioneLibroTitolo(idsLibriTrovati);

      try {
        LibroDao libroDao=new LibroDaoImpl();
        libro=libroDao.getById(libroSelezionato);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    else {
      tornaIndietro=true;
      SelezioneLibroMessaggi.valoriNonPresenti();
    }

  }

  //METODI

  /**
   * Restituisce il libro della libreria
   * selezionato dell'utente.
   *
   * @return il libro
   */

  public Libro getLibro() {
    return libro;
  }

  /**
   * Restituisce il boolean che indica se
   * tornare indietro.
   *
   * @return il boolean se tornare indietro
   */

  public boolean isTornaIndietro() {
    return tornaIndietro;
  }
}
