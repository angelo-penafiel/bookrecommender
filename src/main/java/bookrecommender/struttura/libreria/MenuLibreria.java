package bookrecommender.struttura.libreria;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.dao.daoimpl.LibreriaDaoImpl;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.libreria.MenuLibreriaMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.menu.MenuAzioniLibro;
import java.io.IOException;
import java.util.List;

public class MenuLibreria {

  private int scelta;

  public MenuLibreria(String userId) {

    boolean controllo;

    do {

      controllo=true;

      NuovaSchermata.nuovaSchermata();

      List<Integer> idsLibrerieTrovate;

      LibreriaDao libreriaDao=new LibreriaDaoImpl();
      try {
        idsLibrerieTrovate=libreriaDao.getIdsByUserId(userId);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      if(!idsLibrerieTrovate.isEmpty()) {

        MenuLibreriaMessaggi.menuLibreriaPresente();
        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

        if(scelta==1) {
          var inserimentoLibreria=new InserimentoLibreria(userId);
          controllo=false;
        }

        if(scelta==2) {
          var selezioneLibreria=new SelezioneLibreria(idsLibrerieTrovate);
          var selezioneLibro=new SelezioneLibro(selezioneLibreria.getLibreria());

          if(selezioneLibro.isTornaIndietro()) {
            controllo=false;
          }

          else {

            var menuAzioniLibro=new MenuAzioniLibro();

            if(menuAzioniLibro.getScelta()==3) {
              controllo=false;
            }

            if(menuAzioniLibro.getScelta()==4) {
              scelta=4;
            }

            if(menuAzioniLibro.getScelta()==5) {
              scelta=5;
            }

          }

        }

        if(scelta==3) {
          var selezioneLibreria2 = new SelezioneLibreria(idsLibrerieTrovate);
          var inserimentoLibro = new InserimentoLibro(selezioneLibreria2.getLibreria());
          controllo=false;

        }

      }

      else {

        MenuLibreriaMessaggi.menuLibreriaAssente();
        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(2);

        if(scelta==1) {
          var inserimentoLibreria=new InserimentoLibreria(userId);
          controllo=false;
        }

        if(scelta==2) {
          scelta=4;
        }
      }


    } while (!controllo);

  }

  public int getScelta() {
    return scelta;
  }

}
