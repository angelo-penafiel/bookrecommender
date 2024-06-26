package bookrecommender.interfaccia.libreria;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.dao.daoimpl.LibreriaDaoImpl;
import bookrecommender.elaborazione.entities.Libreria;
import bookrecommender.elaborazione.entities.Libro;
import java.io.IOException;
import java.util.Scanner;

public final class InserimentoLibreriaMessaggi {

  //COSTRUTTORE

  private InserimentoLibreriaMessaggi() {

  }

  //METODO

  public static void intestazioneInserimentoNome() {
    System.out.println("--------------------------------Libreria----------------------------------");
  }

  public static void inserimentoNomeMessaggi() {
    System.out.print("| Inserisci il nome della nuova libreria -> ");
  }

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

  public static void erroreInserimentoNome() {
    System.out.println(
        "  Errore! L'autore deve essere tra" + " " + Libreria.MIN_NOME + " e " + Libreria.MAX_NOME + " caratteri\n");
  }

  public static void erroreInserimentoNomeEsistente() {
    System.out.println(
        "  Errore! Il nome è già stato assegnato a una delle tue librerie\n");
  }

  public static void erroreInserimento() {
    System.out.println("  Errore! \n");
  }
}
