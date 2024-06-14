package bookrecommender.struttura.visualizzazionelibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.VisualizzazioneLibro.VisualizzazioneLIbroMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;

public class VisualizzazioneLibro {

  private Libro libro;

  private int scelta;

  public VisualizzazioneLibro(Libro libro) {

    this.libro=libro;

    NuovaSchermata.nuovaSchermata();

    System.out.println(libro.getTitolo());
    System.out.println(libro.getAutori());
    System.out.println(libro.getAnnoPubblicazione());
    System.out.println(libro.getEditore());
    System.out.println(libro.getCategorie());

    VisualizzazioneLIbroMessaggi.menuScelta();
    scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(3);

  }

  /**
   * Restituisce la scelta effettuata
   * del menu di selezione.
   */

  public int getScelta() {
    return scelta;
  }

}
