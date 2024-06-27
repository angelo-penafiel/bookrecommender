package bookrecommender.interfaccia.libreria;

import java.util.Scanner;

/**
 * Classe che ha la funzione di stampare i
 * messaggi della sezione di selezione di
 * un libro della libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class SelezioneLibroMessaggi {

    //COSTRUTTORE

    private SelezioneLibroMessaggi() {

    }

    //METODO

    /**
     * Stampa il messaggio di libri non
     * presenti nella libreria.
     */

    public static void valoriNonPresenti() {
        System.out.print("\n  Non sono presenti libri nella libreria! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine();
    }
}
