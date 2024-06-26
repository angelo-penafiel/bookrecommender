package bookrecommender.struttura.libreria;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.dao.daoimpl.LibreriaDaoImpl;
import bookrecommender.elaborazione.entities.Libreria;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.libreria.InserimentoLibreriaMessaggi;
import java.io.IOException;

public class InserimentoLibreria {

  private Libreria libreria;

  public InserimentoLibreria(String userId) {

    NuovaSchermata.nuovaSchermata();
    InserimentoLibreriaMessaggi.intestazioneInserimentoNome();
    String nome = InserimentoLibreriaMessaggi.inserimentoNome(userId);

    LibreriaDao libreriaDao=new LibreriaDaoImpl();
    try {
      libreria=libreriaDao.add(nome,userId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  public Libreria getLibreria() {
    return libreria;
  }
}
