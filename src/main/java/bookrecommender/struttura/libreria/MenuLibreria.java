package bookrecommender.struttura.libreria;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.dao.daoimpl.LibreriaDaoImpl;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.libreria.MenuLibreriaMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.menu.MenuAzioniLibro;
import java.io.IOException;
import java.util.List;

/**
 * Classe che ha la funzione di gestire la
 * sezione del menu della libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class MenuLibreria {

  //CAMPO

  /**
   * Campo che indica il numero di scelta
   * effettuata del menu libreria.
   */

  private int scelta;

  //COSTRUTTORE

  /**
   * Restituisce l'oggetto di tipo MenuLibreria
   * e all'interno viene effettuata la stampa
   * del menu e l'inserimento della scelta del
   * menu libreria. Successivamente viene gestito
   * il flusso del programma in base alla scelta
   * effettuata.
   *
   * @param userId indica lo userId dell'utente
   *               loggato
   */

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
          var selezioneLibreria=new SelezioneLibreria(idsLibrerieTrovate, userId);
          var selezioneLibro=new SelezioneLibro(selezioneLibreria.getLibreria());
          Libro l=selezioneLibro.getLibro();
          if(selezioneLibro.isTornaIndietro()) {
            controllo=false;
          }

          else {

            var menuAzioniLibro=new MenuAzioniLibro(userId, l);

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
          var selezioneLibreria2 = new SelezioneLibreria(idsLibrerieTrovate, userId);
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

  //METODO

  /**
   * Restituisce la scelta effettuata
   * del menu libreria.
   *
   * @return la scelta
   */

  public int getScelta() {
    return scelta;
  }

}
