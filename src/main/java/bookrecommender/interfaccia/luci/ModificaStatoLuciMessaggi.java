
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.luci;

import bookrecommender.elaborazione.entities.Luce;
import bookrecommender.elaborazione.factory.Dispositivo;

public final class ModificaStatoLuciMessaggi {

    private static String separatore = "----------------------------------------------------";

    private ModificaStatoLuciMessaggi() {

    }

    public static void separatore() {
        System.out.println(separatore);
    }

    public static void inserimentoScelta() {
        System.out.print(" Scegli la luce da aggiornare -> ");
    }

    public static void stampaLuce(Dispositivo luceModificare) {

        System.out.println("     Nome                      Acceso");
        System.out.println(separatore);

        System.out.print("     "+luceModificare.getNome());

        for(var k=0;k<Luce.MAX_NOME-luceModificare.getNome().length();k++) {
            System.out.print(" ");
        }

        System.out.print(" ");

        if(Boolean.TRUE.equals(luceModificare.getAcceso())) {
            System.out.print("ON\n");
        }
        else {
            System.out.print("OFF\n");
        }

        System.out.println(separatore);
    }

    public static void inserimentoAcceso() {
        System.out.print("| Inserisci (On) per accendere -> ");
    }

    public static void erroreStringa() {
        System.out.println(" Errore! Inserire un numero!\n");
    }

    public static void erroreScelta(int numeroLuci) {
        System.out.println(" Errore! La luce deve essere tra 1 e "+numeroLuci+"\n");
    }

}
