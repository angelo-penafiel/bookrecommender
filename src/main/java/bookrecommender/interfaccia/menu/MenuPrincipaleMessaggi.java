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

    public static void menu(String userId) {

        System.out.println("-----Menu principale/Utente: "+userId+"-----");
        System.out.println("| 1) Cerca libro ");
        System.out.println("| 2) Librerie ");
        System.out.println("| 4) Log out ");
        System.out.println("| 5) Esci dal programma ");
        System.out.println("----------------------------------------------------");
    }

    /**
     * Stampa il menu principale nel caso in cui
     * l'utente non ha effettuato il login.
     */

    public static void menuSenzaRegistrazione() {

        System.out.println("------Menu principale------");
        System.out.println("| 1) Cerca libro          |");
        System.out.println("| 2) Accedi               |");
        System.out.println("| 3) Registrati           |");
        System.out.println("| 4) Esci dal programma   |");
        System.out.println("---------------------------");
    }
}
