
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.menu;

public final class SceltaMenuMessaggi {

    private SceltaMenuMessaggi() {

    }

    public static void inserimento() {
        System.out.print(" Scegli la voce del menu -> ");
    }

    public static void erroreScelte(int scelte) {
        System.out.println(" Errore! La voce deve essere tra 1 e "+scelte+"\n");
    }

    public static void erroreStringa() {
        System.out.println(" Errore! Inserire un numero!\n");
    }
}
