package bookrecommender.interfaccia.menu;

public final class MenuAzioniLibroMessaggi {

    //COSTRUTTORE

    private MenuAzioniLibroMessaggi() {

    }

    //METODI

    public static void menuLibroDaConsigliare() {

        System.out.println("----------Menu azioni libro-----------");
        System.out.println("| 1) Valutazioni                     |");
        System.out.println("| 2) Aggiungi libro ai consigliati   |");
        System.out.println("| 3) Torna al menu libreria          |");
        System.out.println("| 4) Torna al menu principale        |");
        System.out.println("--------------------------------------");
    }

    public static void menuLibroDaNonConsigliare() {

        System.out.println("---------Menu azioni libro---------");
        System.out.println("| 1) Valutazioni                  |");
        System.out.println("| 2) Torna al menu libreria       |");
        System.out.println("| 3) Torna al menu principale     |");
        System.out.println("-----------------------------------");
    }
}
