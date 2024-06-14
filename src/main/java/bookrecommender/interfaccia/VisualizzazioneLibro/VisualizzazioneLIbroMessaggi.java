package bookrecommender.interfaccia.VisualizzazioneLibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.util.List;
import java.util.Scanner;

public final class VisualizzazioneLIbroMessaggi {

    //COSTRUTTORE

    private VisualizzazioneLIbroMessaggi() {

    }

    //METODI

    public static void menuScelta() {
        System.out.println("----------------------------------------------------------");
        System.out.println("| 1) Torna al menu di scelta della modalità di ricerca ");
        System.out.println("| 2) Torna al menu principale ");
        System.out.println("| 3) Esci dal programma");
        System.out.println("----------------------------------------------------------");
    }

}
