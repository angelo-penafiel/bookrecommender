package bookrecommender.interfaccia.menu;

/**
 * Classe che ha la funzione di stampare i
 * messaggi della sezione del menu azioni
 * libro.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class MenuAzioniLibroMessaggi {

    //COSTRUTTORE

    private MenuAzioniLibroMessaggi() {

    }

    //METODO

    /**
     * Stampa il menu azioni libro
     */

    public static void menu() {

        System.out.println("----------Menu azioni libro-----------");
        System.out.println("| 1) Valutazioni                     |");
        System.out.println("| 2) Consigliati                     |");
        System.out.println("| 3) Torna al menu libreria          |");
        System.out.println("| 4) Torna al menu principale        |");
        System.out.println("--------------------------------------");
    }

}
