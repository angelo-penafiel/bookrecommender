package bookrecommender.interfaccia.consigliati;

/**
 * Classe che ha la funzione di stampare i
 * messaggi della sezione del menu consigliati.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class MenuConsigliatiMessaggi {


    //COSTRUTTORE

    private MenuConsigliatiMessaggi() {

    }


    //METODO

    /**
     * Stampa il menu consigliati.
     */

    public static void menu() {

        System.out.println("-------------Menu consigliati--------------");
        System.out.println("| 1) Inserimento libri consigliati        |");
        System.out.println("| 2) Visualizzazione libri consigliati    |");
        System.out.println("| 3) Torna al menu azioni libro           |");
        System.out.println("| 4) Torna al menu libreria               |");
        System.out.println("| 5) Torna al menu principale             |");
        System.out.println("| 6) Log out                              |");
        System.out.println("| 7) Esci dal programma                   |");
        System.out.println("-------------------------------------------");
    }

}
