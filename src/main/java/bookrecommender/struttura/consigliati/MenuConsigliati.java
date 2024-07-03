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

public class MenuConsigliati {

  private int scelta;

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

  public int getScelta() {
    return scelta;
  }
}
