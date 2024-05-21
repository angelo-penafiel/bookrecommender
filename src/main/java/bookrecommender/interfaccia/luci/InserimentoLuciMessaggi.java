
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.luci;

import bookrecommender.elaborazione.entities.Luce;

public final class InserimentoLuciMessaggi {

    private InserimentoLuciMessaggi() {

    }

    public static void menu() {

        System.out.println("| 1) Inserisci luce già esistente ");
        System.out.println("| 2) Inserisci una nuova luce ");
        System.out.println("----------------------------------------------------");
    }

    public static void intestazione() {
        System.out.println("------------------------Inserimento Luce----------------------------");
    }

    public static void inserimentoId() {
        System.out.print("| Inserisci l'id -> ");
    }

    public static void inserimentoNome() {
        System.out.print("| Inserisci il nome -> ");
    }

    public static void inserimentoAcceso() {
        System.out.print("| Inserisci (On) se è accesa -> ");
    }

    public static void erroreLuceNonEsistente() {
        System.out.println(" Errore! La luce non esiste\n");
    }

    public static void erroreLunghezzaNome() {
        System.out.println(" Errore! Il nome deve essere tra" +
                " "+ Luce.MIN_NOME+" e "+ Luce.MAX_NOME+" caratteri\n");
    }

    public static void erroreNomeNonDisponibile() {
        System.out.println(" Errore! Il nome è già stato inserito\n");
    }

    public static void erroreStringa() {
        System.out.println(" Errore! Inserire un numero!\n");
    }

}
