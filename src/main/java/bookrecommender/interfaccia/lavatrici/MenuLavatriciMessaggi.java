
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.lavatrici;

import bookrecommender.elaborazione.entities.Lavatrice;
import bookrecommender.elaborazione.entities.Luce;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public final class MenuLavatriciMessaggi {

    private static String separatore = "---------------------------------------------------" +
            "------------------------------------";

    private MenuLavatriciMessaggi() {

    }

    public static void stampaLavatrici(List<Lavatrice> lavatrici) {

        if (lavatrici.isEmpty()) {
            System.out.println("  Non sono presenti lavatrici\n");
        }

        else {

            intestazioneTabella();

            for(var i=0;i<lavatrici.size();i++) {
                stampaLavatrice(lavatrici,i);
            }
        }
    }

    private static void stampaLavatrice(List<Lavatrice> lavatrici, int i) {

        int j=i+1;

        System.out.print(" ");
        if(j<10) {
            System.out.print(" ");
        }

        System.out.print(j+") "+lavatrici.get(i).getNome());

        for(var k = 0; k< Luce.MAX_NOME-lavatrici.get(i).getNome().length(); k++) {
            System.out.print(" ");
        }

        System.out.print(" ");

        if(Boolean.TRUE.equals(lavatrici.get(i).getAcceso())) {
            System.out.print("ON      ");
        }
        else {
            System.out.print("OFF     ");
        }

        System.out.print(lavatrici.get(i).getTemperatura());

        System.out.print("          ");
        if(lavatrici.get(i).getTemperatura()<100) {
            System.out.print(" ");
        }
        if(lavatrici.get(i).getTemperatura()<10) {
            System.out.print(" ");
        }

        System.out.print(lavatrici.get(i).getTempo()+"     ");

        var europe = ZoneId.of("Europe/Rome");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm").withZone(europe)
                .withLocale(Locale.ITALY);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm").withZone(europe)
                .withLocale(Locale.ITALY);

        if(lavatrici.get(i).getInizio()!=null) {
            System.out.print(lavatrici.get(i).getInizio().format(formatter)+"  ");
            System.out.print(lavatrici.get(i).getFine().format(formatter2)+"  ");
        }

        System.out.print("\n");

        if(i<lavatrici.size()-1) {
            System.out.println("--------------------------------------------------" +
                    "-------------------------------------");
        }
    }

    private static void intestazioneTabella() {
        System.out.println("     Nome                      Acceso  Temperatura  Tempo  Inizio            Fine");
        System.out.println("-------------------------------------------------------------" +
                "--------------------------");
    }

    public static void menuLavatriciNonPresenti() {

        System.out.println(separatore);
        System.out.println("| 1) Inserisci una nuova lavatrice ");
        System.out.println("| 2) Torna al menu principale ");
        System.out.println("| 3) Log out ");
        System.out.println("| 4) Esci dal programma ");
        System.out.println(separatore);
    }

    public static void menuLavatriciPresenti() {

        System.out.println(separatore);
        System.out.println("| 1) Modifica stato lavatrici ");
        System.out.println("| 2) Inserisci una nuova lavatrice ");
        System.out.println("| 3) Elimina lavatrice ");
        System.out.println("| 4) Torna al menu principale ");
        System.out.println("| 5) Log out ");
        System.out.println("| 6) Esci dal programma ");
        System.out.println(separatore);
    }
}
