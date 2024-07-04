package bookrecommender.interfaccia.valutazione;

/**
 * Classe che ha la funzione di stampare i
 * messaggi della sezione del menu valutazione.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class MenuValutazioneMessaggi {


    //COSTRUTTORE

    private MenuValutazioneMessaggi() {

    }


    //METODI

    /**
     * Stampa il menu valutazione nel caso
     * in cui la valutazione è presente.
     */

    public static void menuValutazionePresente() {

        System.out.println("---------Menu valutazione----------");
        System.out.println("| 1) Visualizza valutazioni       |");
        System.out.println("| 2) Torna al menu azioni libro   |");
        System.out.println("| 3) Torna al menu libreria       |");
        System.out.println("| 4) Torna al menu principale     |");
        System.out.println("| 5) Log out                      |");
        System.out.println("| 6) Esci dal programma           |");
        System.out.println("-----------------------------------");
    }

    /**
     * Stampa il menu valutazione nel caso
     * in cui la valutazione non è presente.
     */

    public static void menuValutazioneAssente() {

        System.out.println("---------Menu valutazione----------");
        System.out.println("| 1) Inserimento valutazioni      |");
        System.out.println("| 2) Torna al menu azioni libro   |");
        System.out.println("| 3) Torna al menu libreria       |");
        System.out.println("| 4) Torna al menu principale     |");
        System.out.println("| 5) Log out                      |");
        System.out.println("| 6) Esci dal programma           |");
        System.out.println("-----------------------------------");
    }
}
