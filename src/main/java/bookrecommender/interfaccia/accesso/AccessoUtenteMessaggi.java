
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.accesso;

import bookrecommender.elaborazione.entities.Utente;

public final class AccessoUtenteMessaggi {

    private AccessoUtenteMessaggi() {

    }

    public static void erroreUtenteNonTrovato() {
        System.out.println("  Utente non trovato\n");
    }

    public static void menuSelezione() {

        System.out.println("---------Menu selezione---------");
        System.out.println("| 1) Torna indietro            |");
        System.out.println("| 2) Torna al menu precedente  |");
        System.out.println("--------------------------------");
    }

    public static void titolo() {
        System.out.println("-----------------------------Accesso----------------------------------");
    }

    public static void inserimentoUsername() {
        System.out.print("| Inserisci l'username -> ");
    }

    public static void erroreLunghezzaUsername() {
        System.out.println(" Errore! L'username deve essere tra" +
                " "+ Utente.MIN_USERNAME+" e "+ Utente.MAX_USERNAME+" caratteri\n");
    }

    public static void inserimentoPassword() {
        System.out.print("| Inserisci la password -> ");
    }

    public static void erroreLunghezzaPassword() {
        System.out.println(" Errore! La password deve essere tra" +
                " "+ Utente.MIN_PASSWORD+" e "+ Utente.MAX_PASSWORD+" caratteri\n");
    }
}
