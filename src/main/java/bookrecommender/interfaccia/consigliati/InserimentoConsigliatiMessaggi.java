package bookrecommender.interfaccia.consigliati;

import java.util.Scanner;

public final class InserimentoConsigliatiMessaggi {

    //COSTRUTTORE

    private InserimentoConsigliatiMessaggi() {

    }

    //METODO

    public static void consigliatoAggiunto() {
        System.out.print("\n  Il libro  è stato aggiunto tra i consigliati! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine(  );
    }
}
