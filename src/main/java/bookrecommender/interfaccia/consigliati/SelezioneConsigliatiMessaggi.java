package bookrecommender.interfaccia.consigliati;

import java.util.Scanner;

public final class SelezioneConsigliatiMessaggi {

    //COSTRUTTORE

    private SelezioneConsigliatiMessaggi() {

    }

    //METODO

    public static void consigliatiNonPresenti() {
        System.out.print("\n  Non sono presenti dei libri consigliati! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine(  );
    }

}
