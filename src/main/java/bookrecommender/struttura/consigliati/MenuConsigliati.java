package bookrecommender.struttura.consigliati;

import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.MenuConsigliatiMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.valutazione.MenuValutazioneMessaggi;
import bookrecommender.struttura.valutazione.InserimentoValutazione;
import bookrecommender.struttura.valutazione.ModificaValutazione;

public class MenuConsigliati {

  private int scelta;

  public MenuConsigliati() {

    boolean controllo;

    do {

      controllo=true;

      //verifica se sono presenti libri consigliati
      NuovaSchermata.nuovaSchermata();
      if(true) {

        MenuConsigliatiMessaggi.menuConsigliatiPresenti();
        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(7);

        if(scelta==1) {

          var inserimentoConsigliati=new InserimentoConsigliati();

          controllo=false;

        }

        else if(scelta==2) {

          var selezioneConsigliati=new SelezioneConsigliati();

          var inserimentoConsigliati=new InserimentoConsigliati();

          controllo=false;

        }

      }

      else {

        MenuConsigliatiMessaggi.menuConsigliatiAssenti();
        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(6);

        if(scelta==1) {

          var inserimentoConsigliati=new InserimentoConsigliati();

          controllo=false;

        }

        if(scelta>=2&&scelta<=4) {
          scelta++;
        }
      }

    } while (!controllo);

  }

  public int getScelta() {
    return scelta;
  }
}
