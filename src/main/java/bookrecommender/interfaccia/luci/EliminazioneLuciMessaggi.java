
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.luci;

import bookrecommender.elaborazione.entities.Luce;

import java.util.List;

public final class EliminazioneLuciMessaggi {

    private static String separatore="----------------------------------------------------";

    private EliminazioneLuciMessaggi() {

    }

    public static void separatore() {
        System.out.println(separatore);
    }

    public static void stampaLuci(List<Luce> luci) {

        System.out.println("     Nome                      Acceso");
        System.out.println(separatore);

        for(var i=0;i<luci.size();i++) {

            int j=i+1;

            System.out.print(" ");
            if(j<10) {
                System.out.print(" ");
            }

            System.out.print(j+") "+luci.get(i).getNome());

            for(var k = 0; k< Luce.MAX_NOME-luci.get(i).getNome().length(); k++) {
                System.out.print(" ");
            }

            System.out.print(" ");

            if(Boolean.TRUE.equals(luci.get(i).getAcceso())) {
                System.out.print("ON\n");
            }
            else {
                System.out.print("OFF\n");
            }

            if(i<luci.size()-1) {
                System.out.println(separatore);
            }

        }
    }

    public static void inserimentoScelta() {
        System.out.print(" Scegli la luce da eliminare -> ");
    }

    public static void erroreScelta(int numeroLuci) {
        System.out.println(" Errore! La luce deve essere tra 1 e "+numeroLuci+"\n");
    }

    public static void erroreStringa() {
        System.out.println(" Errore! Inserire un numero!\n");
    }
}
