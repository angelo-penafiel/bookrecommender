package bookrecommender.interfaccia.valutazione;

public final class MenuValutazioneMessaggi {

    //COSTRUTTORE

    private MenuValutazioneMessaggi() {

    }

    //METODO

    public static void menuValutazionePresente() {

        System.out.println("---------Menu valutazione----------");
        System.out.println("| 1) Visualizza valutazioni       |");
        System.out.println("| 2) Torna al menu azioni libro   |");
        System.out.println("| 3) Torna al menu libreria       |");
        System.out.println("| 4) Torna al menu principale     |");
        System.out.println("| 5) Log out                      |");
        System.out.println("| 6) Esci dal programma           |");
        System.out.println("-----------------------------------");
    }

    public static void menuValutazioneAssente() {

        System.out.println("---------Menu valutazione----------");
        System.out.println("| 1) Inserimento valutazioni      |");
        System.out.println("| 2) Torna al menu azioni libro   |");
        System.out.println("| 3) Torna al menu libreria       |");
        System.out.println("| 4) Torna al menu principale     |");
        System.out.println("| 5) Log out                      |");
        System.out.println("| 6) Esci dal programma           |");
        System.out.println("-----------------------------------");
    }
}
