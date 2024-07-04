package bookrecommender.interfaccia.consigliati;

import bookrecommender.elaborazione.entities.Consigliato;
import java.util.Scanner;

/**
 * Classe che ha la funzione di stampare i
 * messaggi e gestire gli inserimenti della
 * sezione di inserimento dei consigliati.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class InserimentoConsigliatiMessaggi {

    //COSTRUTTORE

    private InserimentoConsigliatiMessaggi() {

    }

    //METODO

    /**
     * Stampa il messaggio di errore di inserimento
     * nel caso in cui sia stato raggiunto il numero
     * massimo di libri consigliati.
     */

    public static void consigliatiMax() {
        System.out.print("\n  Non si possono aggiungere altri libri (MAX "
            + Consigliato.MAX_LIBRI_CONSIGLIATI +")! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine(  );
    }

    /**
     * Stampa il messaggio di libro consigliato
     * aggiunto.
     */

    public static void consigliatoAggiunto() {
        System.out.print("\n  Il libro è stato aggiunto tra i consigliati! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine(  );
    }

    /**
     * Stampa il messaggio di errore di
     * inserimento del libro consigliato nel caso
     * sia uguale al corrente.
     */

    public static void erroreLibroCorrente() {
        System.out.print("\n  Il libro è uguale a quello corrente! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine(  );
    }

    /**
     * Stampa il messaggio di errore di
     * inserimento del libro consigliato nel caso
     * sia uguale a uno già consigliato.
     */

    public static void erroreLibroConsigliato() {
        System.out.print("\n  Il libro è già stato consigliato! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine(  );
    }

}
