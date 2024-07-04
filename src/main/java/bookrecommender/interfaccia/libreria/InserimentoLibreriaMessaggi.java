package bookrecommender.interfaccia.libreria;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.dao.daoimpl.LibreriaDaoImpl;
import bookrecommender.elaborazione.entities.Libreria;
import java.io.IOException;
import java.util.Scanner;

/**
 * Classe che ha la funzione di stampare i
 * messaggi e gestire gli inserimenti della
 * sezione di inserimento della libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class InserimentoLibreriaMessaggi {

  //COSTRUTTORE

  private InserimentoLibreriaMessaggi() {

  }

  //METODI

  /**
   * Stampa l'intestazione della sezione di
   * inserimento del nome della libreria.
   */

  public static void intestazioneInserimentoNome() {
    System.out.println("--------------------------------Libreria----------------------------------");
  }

  /**
   * Stampa il messaggio d'inserimento del
   * nome della libreria.
   */

  public static void inserimentoNomeMessaggi() {
    System.out.print("| Inserisci il nome della nuova libreria -> ");
  }

  /**
   * Gestisce l'inserimento del nome
   * della libreria.
   *
   * @return il nome della libreria
   *         inserita dall'utente
   */

  public static String inserimentoNome(String userId) {

    String nome;
    boolean controllo;
    Scanner in = new Scanner(System.in);

    do {
      controllo = true;
      inserimentoNomeMessaggi();
      nome = in.nextLine();
      nome = nome.toLowerCase();

      if (!"".equals(nome)) {
        if (nome.length() < Libreria.MIN_NOME || nome.length() > Libreria.MAX_NOME) {
          controllo = false;
          erroreInserimentoNome();
        }
      } else {
        controllo = false;
        erroreInserimento();
      }

      if (controllo) {
        LibreriaDao libreriaDao = new LibreriaDaoImpl();
        try {
          if (libreriaDao.existsByNome(nome,userId)) {
            controllo=false;
            erroreInserimentoNomeEsistente();
          }
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }


    } while (!controllo);

    return nome;
  }

  /**
   * Stampa il messaggio di errore di
   * inserimento del nome della libreria
   * nel caso in cui il numero di caratteri
   * sfori il minimo e il massimo.
   */

  public static void erroreInserimentoNome() {
    System.out.println(
        "  Errore! L'autore deve essere tra" + " " + Libreria.MIN_NOME + " e " + Libreria.MAX_NOME + " caratteri\n");
  }

  /**
   * Stampa il messaggio di errore di
   * inserimento del nome della libreria
   * nel caso in cui è già presente una
   * libreria con il nome inserito.
   */

  public static void erroreInserimentoNomeEsistente() {
    System.out.println(
        "  Errore! Il nome è già stato assegnato a una delle tue librerie\n");
  }

  /**
   * Stampa il messaggio di errore di
   * inserimento del nome della libreria
   * nel caso in cui è stato inserito
   * nessun carattere.
   */

  public static void erroreInserimento() {
    System.out.println("  Errore! \n");
  }
}
