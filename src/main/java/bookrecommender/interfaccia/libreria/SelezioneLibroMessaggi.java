package bookrecommender.interfaccia.libreria;

import bookrecommender.struttura.libreria.SelezioneLibreria;
import java.util.List;
import java.util.Scanner;

public final class SelezioneLibroMessaggi {

    //COSTRUTTORE

    private SelezioneLibroMessaggi() {

    }

    //METODO

    public static void valoriNonTrovati() {
        System.out.print("\n  Non sono presenti libri nella libreria! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine();
    }
}
