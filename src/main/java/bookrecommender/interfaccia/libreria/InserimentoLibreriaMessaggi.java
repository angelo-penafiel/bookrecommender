package bookrecommender.interfaccia.libreria;

public final class InserimentoLibreriaMessaggi {

    //COSTRUTTORE

    private InserimentoLibreriaMessaggi() {

    }

    //METODO

    public static void menuLibreriaPresente() {

        System.out.println("-----------Menu libreria-----------");
        System.out.println("| 1) Inserisci una libreria       |");
        System.out.println("| 2) Visualizza una libreria      |");
        System.out.println("| 3) Torna al menu principale     |");
        System.out.println("-----------------------------------");
    }

    public static void menuLibreriaAssente() {

        System.out.println("-----------Menu libreria-----------");
        System.out.println("| 1) Inserisci una libreria       |");
        System.out.println("| 2) Torna al menu principale     |");
        System.out.println("-----------------------------------");
    }
}
