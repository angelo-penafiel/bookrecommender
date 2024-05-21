
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.menu;

public final class MenuPrincipaleMessaggi {

    private MenuPrincipaleMessaggi() {

    }

    public static void menu(String username) {

        System.out.println("-----Menu principale/Utente: "+username+"-----");
        System.out.println("| 1) Luci ");
        System.out.println("| 2) Lavatrici ");
        System.out.println("| 3) Notifiche ");
        System.out.println("| 4) Log out ");
        System.out.println("| 5) Esci dal programma ");
        System.out.println("----------------------------------------------------");
    }

    public static void menuSenzaRegistrazione() {

        System.out.println("------Menu principale------");
        System.out.println("| 1) Cerca libro          |");
        System.out.println("| 2) Accedi               |");
        System.out.println("| 3) Registrati           |");
        System.out.println("| 4) Esci dal programma   |");
        System.out.println("---------------------------");
    }
}
