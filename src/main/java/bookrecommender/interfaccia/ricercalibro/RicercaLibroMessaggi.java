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
     */

    public static void menuUtenteRegistratoConsigli() {

        System.out.println("----------------Ricerca libro-----------------");
        System.out.println("| 1) Cerca libro per titolo                  |");
        System.out.println("| 2) Cerca libro per autore                  |");
        System.out.println("| 3) Cerca libro per autore e anno pubbli.   |");
        System.out.println("----------------------------------------------");
    }

    public static void menuUtenteRegistratoLibroLibreria() {

        System.out.println("----------------Ricerca libro-----------------");
        System.out.println("| 1) Cerca libro per titolo                  |");
        System.out.println("| 2) Cerca libro per autore                  |");
        System.out.println("| 3) Cerca libro per autore e anno pubbli.   |");
        System.out.println("----------------------------------------------");
    }

    public static void menuUtenteRegistrato() {

        System.out.println("----------------Ricerca libro-----------------");
        System.out.println("| 1) Cerca libro per titolo                  |");
        System.out.println("| 2) Cerca libro per autore                  |");
        System.out.println("| 3) Cerca libro per autore e anno pubbli.   |");
        System.out.println("| 4) Torna al menu principale                |");
        System.out.println("----------------------------------------------");
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
        System.out.println("| 4) Torna al menu principale                |");
        System.out.println("----------------------------------------------");
    }

}
