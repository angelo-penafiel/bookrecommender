package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.dao.ConsigliatoDao;
import bookrecommender.elaborazione.dao.daoimpl.ConsigliatoDaoImpl;
import bookrecommender.elaborazione.entities.Consigliato;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.MenuConsigliatiMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.valutazione.MenuValutazione;
import bookrecommender.struttura.visualizzazionelibro.VisualizzazioneLibro;
import java.io.IOException;

/**
 * Classe che ha la funzione di gestire la
 * sezione del menu della libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class MenuConsigliati {


  //CAMPO

  /**
   * Campo che indica il numero di scelta
   * effettuata del menu consigliati.
   */

  private int scelta;


  //METODO COSTRUTTORE

  /**
   * Restituisce l'oggetto di tipo MenuConsigliati
   * e all'interno viene effettuata la stampa
   * del menu e l'inserimento della scelta del
   * menu libreria. Successivamente viene gestito
   * il flusso del programma in base alla scelta
   * effettuata.
   *
   * @param userId indica lo userId dell'utente
   *               loggato
   *
   * @param libro indica il libro corrente
   */

  public MenuConsigliati(String userId, Libro libro) {

    boolean controllo;

    do {

      controllo = true;

      Consigliato consigliato;
      ConsigliatoDao consigliatoDao=new ConsigliatoDaoImpl();

      try {
        consigliato=consigliatoDao.getByUserIdAndLibroId(userId,Integer.toString(libro.getId()));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      if(consigliato==null) {

        try {
          consigliatoDao.add(userId,Integer.toString(libro.getId()));
          consigliato=consigliatoDao.getByUserIdAndLibroId(userId,Integer.toString(libro.getId()));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }

      NuovaSchermata.nuovaSchermata();
      MenuConsigliatiMessaggi.menu();
      scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(7);

      if(scelta == 1) {
        var inserimentoConsigliati=new InserimentoConsigliati(consigliato);
        controllo = false;
      }

      if(scelta == 2) {
        var selezioneConsigliati=new SelezioneConsigliati(userId, Integer.toString(libro.getId()));

        controllo=false;
      }

    } while (!controllo);

  }

  //METODO

  /**
   * Restituisce la scelta effettuata
   * del menu consigliati.
   *
   * @return la scelta
   */

  public int getScelta() {
    return scelta;
  }
}
