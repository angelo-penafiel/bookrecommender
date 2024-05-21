
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.luci;

import bookrecommender.elaborazione.entities.Luce;

import java.util.List;

public final class MenuLuciMessaggi {

    private static String separatore = "----------------------------------------------------";

    private MenuLuciMessaggi() {

    }

    public static void stampaLuci(List<Luce> luci) {

        if (luci.isEmpty()) {
            System.out.println("  Non sono presenti luci\n");
        }

        else {

            System.out.println("     Nome                      Acceso");
            System.out.println(separatore);

            for(var i=0;i<luci.size();i++) {
                stampaLuce(luci,i);
            }
        }
    }

    private static void stampaLuce(List<Luce> luci, int i) {

        int j=i+1;

        System.out.print(" ");
        if(j<10) {
            System.out.print(" ");
        }

        System.out.print(j+") "+luci.get(i).getNome());

        for(var k=0;k<Luce.MAX_NOME-luci.get(i).getNome().length();k++) {
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

    public static void menuLuciNonPresenti() {

        System.out.println(separatore);
        System.out.println("| 1) Inserisci una nuova luce ");
        System.out.println("| 2) Torna al menu principale ");
        System.out.println("| 3) Log out ");
        System.out.println("| 4) Esci dal programma ");
        System.out.println(separatore);
    }

    public static void menuLuciPresenti() {

        System.out.println(separatore);
        System.out.println("| 1) Modifica stato luci ");
        System.out.println("| 2) Inserisci una nuova luce ");
        System.out.println("| 3) Elimina luce ");
        System.out.println("| 4) Torna al menu principale ");
        System.out.println("| 5) Log out ");
        System.out.println("| 6) Esci dal programma ");
        System.out.println(separatore);
    }
}
