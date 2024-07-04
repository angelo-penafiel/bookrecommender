package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.dao.ConsigliatoDao;
import bookrecommender.elaborazione.dao.daoimpl.ConsigliatoDaoImpl;
import bookrecommender.elaborazione.entities.Consigliato;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.InserimentoConsigliatiMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.io.IOException;

/**
 * Classe che ha la funzione di gestire la
 * sezione di inserimento del libro cosigliato.
 *
 * @author Angelo Penafiel e Lorenzo Beretta
 * @version 1.0
 */

public class InserimentoConsigliati  {


  //METODO COSTRUTTORE

  /**
   * Restituisce l'oggetto di tipo
   * InserimentoConsigliati.
   *
   * @param consigliato indica l'oggetto Consigliato
   *                    del libro corrente
   */

  public InserimentoConsigliati(Consigliato consigliato) {
    inserisciSuggerimentoLibro(consigliato);
  }


  //METODO

  /**
   * All'interno viene effettuata la selezione del libro
   * e viene aggiunto ai consigliati.
   *
   * @param consigliato indica l'oggetto Consigliato
   *                    del libro corrente
   */

  private void inserisciSuggerimentoLibro(Consigliato consigliato) {

    NuovaSchermata.nuovaSchermata();

    if(consigliato.getLibriConsigliati().size()==Consigliato.MAX_LIBRI_CONSIGLIATI) {
      InserimentoConsigliatiMessaggi.consigliatiMax();
    }

    else {
      var ricercaLibro=new RicercaLibro(2);

      boolean exists=false;

      if(consigliato.getLibroId().equals(Integer.toString(ricercaLibro.getLibro().getId()))) {
        exists=true;
      }

      if(exists) {
        NuovaSchermata.nuovaSchermata();
        InserimentoConsigliatiMessaggi.erroreLibroCorrente();
      }

      else {

        for(String libroConsigliato:consigliato.getLibriConsigliati()) {

          if(Integer.toString(ricercaLibro.getLibro().getId()).equals(libroConsigliato)) {
            exists=true;
          }
        }

        if(exists) {
          NuovaSchermata.nuovaSchermata();
          InserimentoConsigliatiMessaggi.erroreLibroConsigliato();
        }

        else {

          ConsigliatoDao consigliatoDao=new ConsigliatoDaoImpl();

          try {
            consigliatoDao.addLibroConsigliato(consigliato.getId(),
                Integer.toString(ricercaLibro.getLibro().getId()));

          } catch (IOException e) {
            throw new RuntimeException(e);
          }

          NuovaSchermata.nuovaSchermata();
          InserimentoConsigliatiMessaggi.consigliatoAggiunto();

        }

      }

    }

  }

}
