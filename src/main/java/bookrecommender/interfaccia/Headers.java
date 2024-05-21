
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia;

import bookrecommender.elaborazione.entities.Utente;

public final class Headers {

    private static String separatore = "-\n\n";

    private Headers() {

    }

    public static void menuLavatrici(String username) {

        System.out.print("-----Menu lavatrici/Utente: ");
        System.out.print(username);
        for(var i = 0; i< Utente.MAX_USERNAME-username.length(); i++) {
            System.out.print(" ");
        }
        System.out.print("----------------------------------\n\n");
    }

    public static void menuLuci(String username) {

        System.out.print("-----Menu luci/Utente: ");
        System.out.print(username);
        for(var i=0;i<Utente.MAX_USERNAME-username.length();i++) {
            System.out.print(" ");
        }
        System.out.print("----\n\n");
    }

    public static void menuNotifiche(String username) {

        System.out.print("-----Notifiche/Utente: ");
        System.out.print(username);
        for(var i = 0; i< Utente.MAX_USERNAME-username.length(); i++) {
            System.out.print(" ");
        }
        for(var i=0;i<67;i++) {
            System.out.print("-");
        }
        System.out.print("\n\n");
    }

    public static void eliminaLavatrici(String username) {

        System.out.print("-----Elimina lavatrici/Utente: ");
        System.out.print(username);
        for(var i=0;i<Utente.MAX_USERNAME-username.length();i++) {
            System.out.print(" ");
        }
        System.out.print("-------------------------------\n\n");
    }

    public static void inserimentoLavatrici(String username) {

        System.out.print("-----Inserim lavatrice/Utente: ");
        System.out.print(username);
        for(var i=0;i<Utente.MAX_USERNAME-username.length();i++) {
            System.out.print(" ");
        }
        System.out.print(separatore);
    }

    public static void modificaLavatrici(String username) {

        System.out.print("-----Modifica lavatrici/Utente: ");
        System.out.print(username);
        for(var i=0;i<Utente.MAX_USERNAME-username.length();i++) {
            System.out.print(" ");
        }
        System.out.print("-----------------------------\n\n");
    }

    public static void eliminaLuci(String username) {

        System.out.print("-----Elimina luce/Utente: ");
        System.out.print(username);
        for(var i=0;i<Utente.MAX_USERNAME-username.length();i++) {
            System.out.print(" ");
        }
        System.out.print(separatore);
    }

    public static void inserimentoLuci(String username) {

        System.out.print("-----Inserim luce/Utente: ");
        System.out.print(username);
        for(var i=0;i<Utente.MAX_USERNAME-username.length();i++) {
            System.out.print(" ");
        }
        System.out.print(separatore);
    }

    public static void modificaLuci(String username) {

        System.out.print("-----Modifica luce/Utente: ");
        System.out.print(username);
        for(var i=0;i<Utente.MAX_USERNAME-username.length();i++) {
            System.out.print(" ");
        }
        System.out.print(separatore);
    }
}
