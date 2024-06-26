package bookrecommender.interfaccia.libreria;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.dao.daoimpl.LibreriaDaoImpl;
import bookrecommender.elaborazione.entities.Libreria;
import java.io.IOException;
import java.util.Scanner;

public final class InserimentoLibroMessaggi {

  //COSTRUTTORE

  private InserimentoLibroMessaggi() {

  }

  //METODO

  public static void libroPresente() {
    System.out.print("\n  Il libro selezionato è già presente! Inserisci qualsiasi tasto: ");
    Scanner in=new Scanner(System.in);
    in.nextLine();
  }

}
