package bookrecommender.interfaccia.valutazione;

public final class InserimentoValutazioneMessaggi {

    //COSTRUTTORE

    private InserimentoValutazioneMessaggi() {

    }

    //METODO

    public static void menuValutazionePresente() {

        System.out.println("---------Menu valutazione----------");
        System.out.println("| 1) Inserimento valutazione      |");
        System.out.println("| 2) Modifica valutazione         |");
        System.out.println("| 3) Torna al menu azioni libro   |");
        System.out.println("| 4) Torna al menu libreria       |");
        System.out.println("| 5) Torna al menu principale     |");
        System.out.println("-----------------------------------");
    }

    public static void menuValutazioneAssente() {

        System.out.println("---------Menu valutazione----------");
        System.out.println("| 1) Inserimento valutazione      |");
        System.out.println("| 2) Torna al menu azioni libro   |");
        System.out.println("| 3) Torna al menu libreria       |");
        System.out.println("| 4) Torna al menu principale     |");
        System.out.println("-----------------------------------");
    }
}
