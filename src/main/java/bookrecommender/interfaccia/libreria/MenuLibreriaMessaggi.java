package bookrecommender.interfaccia.libreria;

/**
 * Classe che ha la funzione di stampare i
 * messaggi della sezione del menu libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class MenuLibreriaMessaggi {

    //COSTRUTTORE

    private MenuLibreriaMessaggi() {

    }

    //METODO

    /**
     * Stampa il menu libreria nel caso in
     * cui è presente una libreria dell'utente
     * loggato.
     */

    public static void menuLibreriaPresente() {

        System.out.println("---------------Menu libreria--------------");
        System.out.println("| 1) Inserisci una libreria              |");
        System.out.println("| 2) Visualizza una libreria             |");
        System.out.println("| 3) Inserisci un libro nella libreria   |");
        System.out.println("| 4) Torna al menu principale            |");
        System.out.println("------------------------------------------");
    }

    /**
     * Stampa il menu libreria nel caso in
     * cui non è presente una libreria
     * dell'utente loggato.
     */

    public static void menuLibreriaAssente() {

        System.out.println("-----------Menu libreria-----------");
        System.out.println("| 1) Inserisci una libreria       |");
        System.out.println("| 2) Torna al menu principale     |");
        System.out.println("-----------------------------------");
    }
}
