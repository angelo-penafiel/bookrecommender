package bookrecommender.struttura.menu;

import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.MenuAzioniLibroMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.consigliati.MenuConsigliati;
import bookrecommender.struttura.valutazione.MenuValutazione;

public class MenuAzioniLibro {

  private int scelta;

  public MenuAzioniLibro(String userID ) {

    boolean controllo;

    do {

      controllo=true;

      NuovaSchermata.nuovaSchermata();
      MenuAzioniLibroMessaggi.menu();
      scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

      if(scelta==1) {

        var menuValutazione=new MenuValutazione();

        if(menuValutazione.getScelta()==3) {
          controllo=false;
        }

        if(menuValutazione.getScelta()==4) {
          scelta=3;
        }

        if(menuValutazione.getScelta()==5) {
          scelta=4;
        }


      }

      if(scelta==2) {

        var menuConsigliati=new MenuConsigliati(userID);

        if(menuConsigliati.getScelta()==3) {
          controllo=false;
        }

        if(menuConsigliati.getScelta()==4) {
          scelta=3;
        }

        if(menuConsigliati.getScelta()==5) {
          scelta=4;
        }

        if(menuConsigliati.getScelta()==6) {
          scelta=5;
        }
      }

    } while (!controllo);

  }

  public int getScelta() {
    return scelta;
  }
}
