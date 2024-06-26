package bookrecommender.struttura.libreria;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.dao.daoimpl.LibreriaDaoImpl;
import bookrecommender.elaborazione.entities.Libreria;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.libreria.InserimentoLibroMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.io.IOException;

public class InserimentoLibro {

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
