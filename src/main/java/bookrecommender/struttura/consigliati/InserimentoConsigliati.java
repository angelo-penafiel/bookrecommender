package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.struttura.ricercalibro.RicercaLibro;

public class InserimentoConsigliati {

  private int scelta;

  public InserimentoConsigliati() {

    var ricercaLibro=new RicercaLibro(2);
    Libro libro = ricercaLibro.getLibro();
    scelta = ricercaLibro.getScelta();

  }

  public int getScelta() {
    return scelta;
  }
}
