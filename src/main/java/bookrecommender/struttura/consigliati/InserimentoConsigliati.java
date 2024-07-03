package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.dao.ConsigliatoDao;
import bookrecommender.elaborazione.dao.daoimpl.ConsigliatoDaoImpl;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.InserimentoConsigliatiMessaggi;
import java.io.IOException;

public class InserimentoConsigliati  {

  public InserimentoConsigliati(String UserID, Libro libro) {

    NuovaSchermata.nuovaSchermata();

    ConsigliatoDao consigliatoDao =new ConsigliatoDaoImpl();

    try {
      consigliatoDao.addLibro(UserID,libro);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    InserimentoConsigliatiMessaggi.consigliatoAggiunto();

  }

}
