package bookrecommender.struttura.visualizzazionelibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.visualizzazioneLibro.VisualizzazioneLIbroMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;

public class VisualizzazioneLibro {

  private Libro libro;

  private int scelta;

  public VisualizzazioneLibro(int menuProvenienza, Libro libro) {

    this.libro=libro;

    NuovaSchermata.nuovaSchermata();

    System.out.println(libro.getTitolo());
    System.out.println(libro.getAutori());
    System.out.println(libro.getAnnoPubblicazione());
    System.out.println(libro.getEditore());
    System.out.println(libro.getCategorie());

    if(menuProvenienza==0) {
      VisualizzazioneLIbroMessaggi.menuSceltaSenzaRegistrazione();
      scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(3);
    }

    if(menuProvenienza==1) {
      VisualizzazioneLIbroMessaggi.menuSceltaUtenteRegistrato();
      scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);
    }

    if(menuProvenienza==2) {
      VisualizzazioneLIbroMessaggi.menuSceltaConsigliati();
      scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(6);
    }

  }

  /**
   * Restituisce la scelta effettuata
   * del menu di selezione.
   */

  public int getScelta() {
    return scelta;
  }

}
