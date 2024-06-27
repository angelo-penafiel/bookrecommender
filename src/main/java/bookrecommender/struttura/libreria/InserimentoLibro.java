package bookrecommender.struttura.libreria;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.dao.daoimpl.LibreriaDaoImpl;
import bookrecommender.elaborazione.entities.Libreria;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.libreria.InserimentoLibroMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.io.IOException;

/**
 * Classe che ha la funzione di gestire la
 * sezione di inserimento del libro nella libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class InserimentoLibro {

  //COSTRUTTORE

  /**
   * Restituisce l'oggetto di tipo  InserimentoLibro
   * e all'interno viene effettuata la selezione
   * del libro da aggiungere alla libreria data.
   *
   * @param libreria indica la libreria
   */

  public InserimentoLibro(Libreria libreria) {

    RicercaLibro ricercaLibro=new RicercaLibro(3);

    LibreriaDao libreriaDao=new LibreriaDaoImpl();
    try {

      if(libreriaDao.existsLibro(libreria,ricercaLibro.getLibro())) {

        NuovaSchermata.nuovaSchermata();
        InserimentoLibroMessaggi.libroPresente();

      }

      else {
        libreriaDao.addLibro(libreria,ricercaLibro.getLibro());
      }


    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
