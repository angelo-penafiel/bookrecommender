
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.lavatrici;

import bookrecommender.elaborazione.entities.Lavatrice;

public final class InserimentoLavatriciMessaggi {

    private InserimentoLavatriciMessaggi() {

    }

    public static void menu() {

        System.out.println("| 1) Inserisci lavatrice già esistente ");
        System.out.println("| 2) Inserisci una nuova lavatrice ");
        System.out.println("---------------------------------------------------------");
    }

    public static void intestazione() {
        System.out.println("------------------------Inserimento lavatrice----------------------------");
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

    public static void inserimentoStatoLavaggio() {
        System.out.print("| Inserisci (Si) se sta lavando -> ");
    }

    public static void inserimentoTemperatura() {
        System.out.print("| Inserisci la temperatura (°C) -> ");
    }

    public static void inserimentoTempo() {
        System.out.print("| Inserisci il tempo (min) -> ");
    }

    public static void erroreLavatriceNonEsistente() {
        System.out.println(" Errore! La lavatrice non esiste\n");
    }

    public static void erroreLunghezzaNome() {
        System.out.println(" Errore! Il nome deve essere tra" +
                " "+ Lavatrice.MIN_NOME+" e "+ Lavatrice.MAX_NOME+" caratteri\n");
    }

    public static void erroreNomeNonDisponibile() {
        System.out.println(" Errore! Il nome è già stato inserito\n");
    }

    public static void erroreTemperatura() {
        System.out.println(" Errore! La temperatura deve essere tra "+Lavatrice.MIN_TEMPERATURA
                +" e "+Lavatrice.MAX_TEMPERATURA+" °C\n");
    }

    public static void erroreStringa() {
        System.out.println(" Errore! Inserire un numero!\n");
    }

    public static void erroreTempo() {
        System.out.println(" Errore! Il tempo deve essere tra "+Lavatrice.MIN_TEMPO
                +" e "+Lavatrice.MAX_TEMPO+" minuti\n");
    }
}
