
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.ricercalibro;

public final class RicercaLibroMessaggi {

    private RicercaLibroMessaggi() {

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

        System.out.println("----------------Ricerca libro-----------------");
        System.out.println("| 1) Cerca libro per titolo                  |");
        System.out.println("| 2) Cerca libro per autore                  |");
        System.out.println("| 3) Cerca libro per autore e anno pubbli.   |");
        System.out.println("| 4) Accedi                                  |");
        System.out.println("| 5) Registrati                              |");
        System.out.println("| 6) Esci dal programma                      |");
        System.out.println("----------------------------------------------");
    }

    public static void erroreScelte(int sceltaMinore, int sceltaMaggiore) {
        System.out.println("      Errore! La voce deve essere tra "+sceltaMinore+" e "+sceltaMaggiore+"\n");
    }

    public static void erroreStringa() {
        System.out.println("      Errore!\n");
    }

}
