package bookrecommender.interfaccia.consigliati;

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
        System.out.println("-----------------------------------------");
    }

    public static void menuConsigliatiAssenti() {

        System.out.println("------------Menu consigliati-------------");
        System.out.println("| 1) Inserimento libri consigliati      |");
        System.out.println("| 2) Torna al menu azioni libro         |");
        System.out.println("| 3) Torna al menu libreria             |");
        System.out.println("| 4) Torna al menu principale           |");
        System.out.println("-----------------------------------------");
    }
}
