package bookrecommender.interfaccia.visualizzazioneLibro;

import java.util.Scanner;

public final class VisualizzazioneLIbroMessaggi {

    //COSTRUTTORE

    private VisualizzazioneLIbroMessaggi() {

    }

    //METODI

    public static void menuSceltaSenzaRegistrazione() {
        System.out.println("----------------------------------------------------------");
        System.out.println("| 1) Torna al menu di scelta della modalità di ricerca ");
        System.out.println("| 2) Torna al menu principale ");
        System.out.println("| 3) Esci dal programma");
        System.out.println("----------------------------------------------------------");
    }

    public static void menuSceltaUtenteRegistrato() {
        System.out.println("----------------------------------------------------------");
        System.out.println("| 1) Torna al menu di scelta della modalità di ricerca ");
        System.out.println("| 2) Torna al menu principale ");
        System.out.println("| 3) Log out");
        System.out.println("| 4) Esci dal programma");
        System.out.println("----------------------------------------------------------");
    }

}
