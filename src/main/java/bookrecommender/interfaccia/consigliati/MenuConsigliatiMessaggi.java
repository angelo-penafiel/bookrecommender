package bookrecommender.interfaccia.consigliati;

import java.util.Scanner;

public final class MenuConsigliatiMessaggi {

    //COSTRUTTORE

    private MenuConsigliatiMessaggi() {

    }

    //METODO

    public static void menuConsigliatiPresenti() {

        System.out.println("------------Menu consigliati-------------");
        System.out.println("| 1) Inserimento libri consigliati      |");
        System.out.println("| 2) Sostituizione libri consigliati    |");
        System.out.println("| 3) Torna al menu azioni libro         |");
        System.out.println("| 4) Torna al menu libreria             |");
        System.out.println("| 5) Torna al menu principale           |");
        System.out.println("| 6) Log out                            |");
        System.out.println("| 7) Esci dal programma                 |");
        System.out.println("-----------------------------------------");
    }

    public static void menuConsigliatiAssenti() {

        System.out.println("------------Menu consigliati-------------");
        System.out.println("| 1) Inserimento libri consigliati      |");
        System.out.println("| 2) Torna al menu azioni libro         |");
        System.out.println("| 3) Torna al menu libreria             |");
        System.out.println("| 4) Torna al menu principale           |");
        System.out.println("| 5) Log out                            |");
        System.out.println("| 6) Esci dal programma                 |");
        System.out.println("-----------------------------------------");
    }

}
