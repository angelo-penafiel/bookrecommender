
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.notifiche;

import bookrecommender.elaborazione.entities.Notifica;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public final class GestioneNotificheMessaggi {

    private GestioneNotificheMessaggi() {

    }

    public static void stampaNotifiche(List<Notifica> notifiche) {

        if (notifiche.isEmpty()) {
            System.out.println("  Non sono presenti notifiche\n");
        }

        else {

            stampaIntestazione();

            for(var i=0;i<notifiche.size();i++) {

               stampaNotifica(notifiche,i);

            }
        }
    }

    private static void stampaIntestazione() {

        System.out.print("     Testo");
        for(var i=0;i<82;i++) {
            System.out.print(" ");
        }
        System.out.print("Timestamp\n");
        for(var i=0;i<115;i++) {
            System.out.print("-");
        }
        System.out.print("\n");
    }

    private static void stampaNotifica(List<Notifica> notifiche, int i) {

        int j=i+1;

        System.out.print(" ");
        if(j<10) {
            System.out.print(" ");
        }

        System.out.print(j+") "+notifiche.get(i).getTesto()+"  ");

        for(var k=0;k<85-notifiche.get(i).getTesto().length();k++) {
            System.out.print(" ");
        }

        var europe = ZoneId.of("Europe/Rome");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")
                .withZone(europe).withLocale(Locale.ITALY);
        System.out.print(notifiche.get(i).getLocalDateTime().format(formatter));

        System.out.print("\n");

        if(i<notifiche.size()-1) {
            for(var k =0;k<115;k++) {
                System.out.print("-");
            }
            System.out.print("\n");
        }
    }

    public static void menu() {

        for(var i=0;i<115;i++) {
            System.out.print("-");
        }
        System.out.print("\n");
        System.out.println("| 1) Torna al menu principale ");
        System.out.println("| 2) Log out ");
        System.out.println("| 3) Esci dal programma ");
        for(var i=0;i<115;i++) {
            System.out.print("-");
        }
        System.out.print("\n");
    }
}
