package bookrecommender.interfaccia.consigliati;

import java.util.Scanner;

/**
 * Classe che ha la funzione di stampare i
 * messaggi e gestire gli inserimenti della
 * sezione della selezione dei consigliati.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class SelezioneConsigliatiMessaggi {


    //COSTRUTTORE

    private SelezioneConsigliatiMessaggi() {

    }


    //METODO

    /**
     * Stampa il messaggio di libri consigliati
     * non presenti.
     */

    public static void consigliatiNonPresenti() {
        System.out.print("\n  Non sono presenti dei libri consigliati! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine(  );
    }

}
