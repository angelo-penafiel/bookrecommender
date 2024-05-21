
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.lavatrici;

public final class EliminazioneLavatriciMessaggi {

    private EliminazioneLavatriciMessaggi() {

    }

    public static void separatoreScelta() {
        System.out.println("-------------------------------------------------------------" +
                "--------------------------");
    }

    public static void inserimentoScelta() {
        System.out.print(" Scegli la lavatrice da eliminare -> ");
    }

    public static void erroreScelta(int numeroLavatrici) {
        System.out.println(" Errore! La lavatrice deve essere tra 1 e "+numeroLavatrici+"\n");
    }

    public static void erroreStringa() {
        System.out.println(" Errore! Inserire un numero!\n");
    }
}
