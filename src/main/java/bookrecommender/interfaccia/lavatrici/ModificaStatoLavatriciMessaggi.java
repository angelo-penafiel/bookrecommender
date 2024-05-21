
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.lavatrici;

import bookrecommender.elaborazione.entities.Lavatrice;
import bookrecommender.elaborazione.entities.Luce;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class ModificaStatoLavatriciMessaggi {

    private static String separatore = "------------------------------------------" +
            "---------------------------------------------";

    private ModificaStatoLavatriciMessaggi() {

    }

    public static void separatore() {
        System.out.println("-----------------------------------------------------------" +
                "----------------------------");
    }

    public static void inserimentoScelta() {
        System.out.print(" Scegli la lavatrice da aggiornare -> ");
    }

    public static void stampaLavatrice(Lavatrice lavatriceModificare) {

        System.out.println("     Nome                      Acceso  Temperatura  Tempo  Inizio            Fine");
        System.out.println(separatore);

        System.out.print("     "+ lavatriceModificare.getNome());

        for(var k = 0; k< Luce.MAX_NOME- lavatriceModificare.getNome().length(); k++) {
            System.out.print(" ");
        }

        System.out.print(" ");

        if(Boolean.TRUE.equals(lavatriceModificare.getAcceso())) {
            System.out.print("ON      ");
        }
        else {
            System.out.print("OFF     ");
        }

        System.out.print(lavatriceModificare.getTemperatura());

        System.out.print("          ");
        if(lavatriceModificare.getTemperatura()<100) {
            System.out.print(" ");
        }
        if(lavatriceModificare.getTemperatura()<10) {
            System.out.print(" ");
        }

        System.out.print(lavatriceModificare.getTempo()+"     ");

        var europe = ZoneId.of("Europe/Rome");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm").withZone(europe)
                .withLocale(Locale.ITALY);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm").withZone(europe)
                .withLocale(Locale.ITALY);

        if(lavatriceModificare.getInizio()!=null) {
            System.out.print(lavatriceModificare.getInizio().format(formatter)+"  ");
            System.out.print(lavatriceModificare.getFine().format(formatter2)+"  ");
        }

        System.out.print("\n");

    }

    public static void menu() {

        System.out.println(separatore);
        System.out.println("| 1) Modifica stato di accensione");
        System.out.println("| 2) Modifica lavaggio ");
        System.out.println("| 3) Torna al menu lavatrici ");
        System.out.println("| 4) Torna al menu principale ");
        System.out.println("| 5) Log out ");
        System.out.println("| 6) Esci dal programma ");
        System.out.println(separatore);
    }

    public static void inserimentoAcceso() {
        System.out.print("| Inserisci (On) per accendere -> ");
    }

    public static void inserimentoStatoLavaggio() {
        System.out.print("| Inserisci (Si) per lavare -> ");
    }

    public static void inserimentoTemperatura() {
        System.out.print("| Inserisci la temperatura (°C) -> ");
    }

    public static void inserimentoTempo() {
        System.out.print("| Inserisci il tempo (min) -> ");
    }

    public static void erroreStringa() {
        System.out.println(" Errore! Inserire un numero!\n");
    }

    public static void erroreScelta(int numeroLavatrici) {
        System.out.println(" Errore! La lavatrice deve essere tra 1 e "+numeroLavatrici+"\n");
    }

    public static void erroreTemperatura () {
        System.out.println(" Errore! La temperatura deve essere tra "+Lavatrice.MIN_TEMPERATURA
                +" e "+Lavatrice.MAX_TEMPERATURA+" °C\n");
    }

    public static void erroreTempo () {
        System.out.println(" Errore! Il tempo deve essere tra "+Lavatrice.MIN_TEMPO
                +" e "+Lavatrice.MAX_TEMPO+" minuti\n");
    }
}
