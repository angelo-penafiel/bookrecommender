package bookrecommender.interfaccia.ricercalibro;

/**
 * Classe che ha la funzione di stampare i
 * messaggi della sezione di selezione di
 * ricerca dei libri.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class RicercaLibroMessaggi {

    //COSTRUTTORE

    private RicercaLibroMessaggi() {

    }

    //METODI

    /**
     * Stampa il menu della sezione di scelta
     * della modalità di ricerca dei libri nel
     * caso in cui l'utente è loggato.
     *
     * @param userId rappresenta l'userId
     */

    public static void menu(String userId) {

        System.out.println("-----Menu principale/Utente: "+userId+"-----");
        System.out.println("| 1) Luci ");
        System.out.println("| 2) Lavatrici ");
        System.out.println("| 3) Notifiche ");
        System.out.println("| 4) Log out ");
        System.out.println("| 5) Esci dal programma ");
        System.out.println("----------------------------------------------------");
    }

    /**
     * Stampa il menu della sezione di scelta
     * della modalità di ricerca dei libri nel
     * caso in cui l'utente non ha effettuato
     * il login.
     */

    public static void menuSenzaRegistrazione() {

        System.out.println("----------------Ricerca libro-----------------");
        System.out.println("| 1) Cerca libro per titolo                  |");
        System.out.println("| 2) Cerca libro per autore                  |");
        System.out.println("| 3) Cerca libro per autore e anno pubbli.   |");
        System.out.println("| 4) Accedi                                  |");
        System.out.println("| 5) Registrati                              |");
        System.out.println("| 6) Esci dal programma                      |");
        System.out.println("----------------------------------------------");
    }

}
