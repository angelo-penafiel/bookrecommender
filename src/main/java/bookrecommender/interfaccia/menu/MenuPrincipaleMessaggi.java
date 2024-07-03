package bookrecommender.interfaccia.menu;

/**
 * Classe che ha la funzione di stampare i
 * messaggi del menu principale.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class MenuPrincipaleMessaggi {

    //COSTRUTTORE

    private MenuPrincipaleMessaggi() {

    }

    //METODI

    /**
     * Stampa il menu principale nel caso in cui
     * l'utente è loggato.
     *
     * @param userId rappresenta l'userId
     */

    /**
     * Stampa il menu principale nel caso in cui
     * l'utente non ha effettuato il login.
     */

    public static void menuSenzaRegistrazione() {

        System.out.println("------Menu principale------");
        System.out.println("| 1) Ricerca libro        |");
        System.out.println("| 2) Accedi               |");
        System.out.println("| 3) Registrazione        |");
        System.out.println("| 4) Esci dal programma   |");
        System.out.println("---------------------------");
    }

    public static void menuRegistrazione() {

        System.out.println("------Menu principale------");
        System.out.println("| 1) Ricerca libro        |");
        System.out.println("| 2) Librerie             |");
        System.out.println("| 3) Log out              |");
        System.out.println("| 4) Esci dal programma   |");
        System.out.println("---------------------------");
    }

}
