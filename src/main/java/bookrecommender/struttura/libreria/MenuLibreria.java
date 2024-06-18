package bookrecommender.struttura.libreria;

import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.libreria.MenuLibreriaMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.menu.MenuAzioniLibro;

public class MenuLibreria {

  private int scelta;

  public MenuLibreria() {

    boolean controllo;

    do {

      controllo=true;

      //Caricamento oggetto libreria

      //Verifico se è presente almeno una libreria
      NuovaSchermata.nuovaSchermata();
      if(true) {

        MenuLibreriaMessaggi.menuLibreriaPresente();
        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(3);

        if(scelta==1) {
          var inserimentoLibreria=new InserimentoLibreria();
          controllo=false;
        }

        if(scelta==2) {
          var selezioneLibreria=new SelezioneLibreria();
          var selezioneLibro=new SelezioneLibro();
          var menuAzioniLibro=new MenuAzioniLibro();

          if(menuAzioniLibro.getScelta()==3) {
            controllo=false;
          }

          if(menuAzioniLibro.getScelta()==4) {
            scelta=3;
          }

          if(menuAzioniLibro.getScelta()==5) {
            scelta=4;
          }

        }

      }

      else{

        MenuLibreriaMessaggi.menuLibreriaAssente();
        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(2);

        if(scelta==1) {
          var inserimentoLibreria=new InserimentoLibreria();
          controllo=false;
        }

        if(scelta==2) {
          scelta=3;
        }
      }


    } while (!controllo);

  }

  public int getScelta() {
    return scelta;
  }

}
