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

public class SelezioneLibro {

  private Libro libro;

  private boolean tornaIndietro;

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
      SelezioneLibroMessaggi.valoriNonTrovati();
    }

  }

  public Libro getLibro() {
    return libro;
  }

  public boolean isTornaIndietro() {
    return tornaIndietro;
  }
}
