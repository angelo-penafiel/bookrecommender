package bookrecommender.interfaccia.libreria;

import java.util.Scanner;

/**
 * Classe che ha la funzione di stampare i
 * messaggi della sezione di inserimento di
 * un libro nella libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class InserimentoLibroMessaggi {

  //COSTRUTTORE

  private InserimentoLibroMessaggi() {

  }

  //METODO

  /**
   * Stampa il messaggio di errore di
   * inserimento del libro nella libreria
   * nel caso in cui è già presente nella
   * libreria.
   */

  public static void libroPresente() {
    System.out.print("\n  Il libro selezionato è già presente! Inserisci qualsiasi tasto: ");
    Scanner in=new Scanner(System.in);
    in.nextLine();
  }

}
