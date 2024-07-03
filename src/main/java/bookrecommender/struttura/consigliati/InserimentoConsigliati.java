package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.dao.ConsigliatoDao;
import bookrecommender.elaborazione.dao.daoimpl.ConsigliatoDaoImpl;
import bookrecommender.elaborazione.entities.Consigliato;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.InserimentoConsigliatiMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.io.IOException;

public class InserimentoConsigliati  {

  public InserimentoConsigliati(Consigliato consigliato) {

    NuovaSchermata.nuovaSchermata();

    if(consigliato.getLibriConsigliati().size()==Consigliato.MAX_LIBRI_CONSIGLIATI) {
      InserimentoConsigliatiMessaggi.consigliatiMax();
    }

    else {
      var ricercaLibro=new RicercaLibro(2);

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
