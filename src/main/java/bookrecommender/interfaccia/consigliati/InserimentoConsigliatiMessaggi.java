package bookrecommender.interfaccia.consigliati;

import bookrecommender.elaborazione.entities.Consigliato;
import java.util.Scanner;

public final class InserimentoConsigliatiMessaggi {

    //COSTRUTTORE

    private InserimentoConsigliatiMessaggi() {

    }

    //METODO

    public static void consigliatiMax() {
        System.out.print("\n  Non si possono aggiungere altri libri (MAX "
            + Consigliato.MAX_LIBRI_CONSIGLIATI +")! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine(  );
    }

    public static void consigliatoAggiunto() {
        System.out.print("\n  Il libro è stato aggiunto tra i consigliati! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine(  );
    }
}
