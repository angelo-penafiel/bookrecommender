package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.dao.ConsigliatiDao;
import bookrecommender.elaborazione.dao.daoimpl.ConsigliatiDaoImpl;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.InserimentoConsigliatiMessaggi;
import java.io.IOException;

public class InserimentoConsigliati  {

  public InserimentoConsigliati(String UserID, Libro libro) {

    NuovaSchermata.nuovaSchermata();

    ConsigliatiDao consigliatiDao=new ConsigliatiDaoImpl();

    try {
      consigliatiDao.addLibro(UserID,libro);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    InserimentoConsigliatiMessaggi.consigliatoAggiunto();

  }

}
