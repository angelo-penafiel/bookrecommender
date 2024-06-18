package bookrecommender.struttura.valutazione;

import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.valutazione.ModificaValutazioneMessaggi;

public class ModificaValutazione {

  private int scelta;

  public ModificaValutazione() {

    NuovaSchermata.nuovaSchermata();
    ModificaValutazioneMessaggi.menu();
    scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(5);

    if(scelta==1) {

    }

    if(scelta==2) {

    }

    if(scelta==3) {

    }

    if(scelta==4) {

    }

    if(scelta==5) {

    }

  }

}
