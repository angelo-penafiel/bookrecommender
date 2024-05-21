
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.registrazione;

import bookrecommender.elaborazione.entities.Utente;

public final class RegistrazioneUtenteMessaggi {

    private RegistrazioneUtenteMessaggi() {

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
        System.out.println("--------------------------Registrazione-------------------------------");
    }

    public static void inserimentoUsername() {
        System.out.print("| Inserisci l'username -> ");
    }

    public static void erroreLunghezzaUsername() {
        System.out.println(" Errore! L'username deve essere tra" +
                " "+ Utente.MIN_USERNAME+" e "+ Utente.MAX_USERNAME+" caratteri\n");
    }

    public static void erroreUsernameNonDisponibile() {
        System.out.println(" Errore! L'username è già stato inserito\n");
    }

    public static void inserimentoPassword() {
        System.out.print("| Inserisci la password -> ");
    }

    public static void erroreLunghezzaPassword() {
        System.out.println(" Errore! La password deve essere tra" +
                " "+ Utente.MIN_PASSWORD+" e "+ Utente.MAX_PASSWORD+" caratteri\n");
    }
}
