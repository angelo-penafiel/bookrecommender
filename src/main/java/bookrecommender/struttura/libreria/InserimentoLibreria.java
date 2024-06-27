package bookrecommender.struttura.libreria;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.dao.daoimpl.LibreriaDaoImpl;
import bookrecommender.elaborazione.entities.Libreria;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.libreria.InserimentoLibreriaMessaggi;
import java.io.IOException;

/**
 * Classe che ha la funzione di gestire la
 * sezione di inserimento della libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class InserimentoLibreria {

  /**
   * Campo che indica la libreria
   * inserita dall'utente.
   */

  private Libreria libreria;

  //COSTRUTTORE

  /**
   * Restituisce l'oggetto di tipo InserimentoLibreria
   * e all'interno viene effettuato l'inserimento della
   * libreria dell'utente dato.
   *
   * @param userId indica lo userId
   */

  public InserimentoLibreria(String userId) {

    registraLibreria(userId);

  }

  //METODI

  /**
   * Effettua la registrazione di una nuova libreria
   * dell'utente.
   *
   * @param userId indica lo userId
   */

  private void registraLibreria(String userId) {

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

  /**
   * Restituisce la libreria
   * inserita dell'utente.
   *
   * @return la libreria
   */

  public Libreria getLibreria() {
    return libreria;
  }

}
