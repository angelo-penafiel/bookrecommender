package bookrecommender.interfaccia.consigliati;

import java.util.Scanner;

public final class InserimentoConsigliatiMessaggi {

    //COSTRUTTORE

    private InserimentoConsigliatiMessaggi() {

    }

    //METODO

    public static void cosigliatoAggiunto() {
        System.out.print("\n  Il libro consigliato è stato aggiunto! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine();
    }
}
