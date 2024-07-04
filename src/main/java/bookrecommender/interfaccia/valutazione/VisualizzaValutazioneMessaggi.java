package bookrecommender.interfaccia.valutazione;

import java.util.Scanner;

/**
 * Classe che ha la funzione di stampare i
 * messaggi della sezione di visualizzazione
 * della valutazione.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class VisualizzaValutazioneMessaggi {


    //COSTRUTTORE

    private VisualizzaValutazioneMessaggi() {

    }


    //METODI

    /**
     * Stampa l'intestazione della sezione
     */

    public static void intestazione() {
        System.out.println("----------------------------------Valutazione-----------------------------------");
    }

    /**
     * Stampa le valutazioni
     *
     * @param valutazioni rappresenta le
     *                    valutazioni
     */

    public static void valutazioni(String [] valutazioni) {

        System.out.print("\n                            Note\n\n");
        System.out.print("  Stile: "+valutazioni[2]+"/5                ");
        stampaNota(valutazioni[3]);
        System.out.print("  Contenuto: "+valutazioni[4]+"/5            ");
        stampaNota(valutazioni[5]);
        System.out.print("  Gradevolezza: "+valutazioni[6]+"/5         ");
        stampaNota(valutazioni[7]);
        System.out.print("  Originalità: "+valutazioni[8]+"/5          ");
        stampaNota(valutazioni[9]);
        System.out.print("  Edizione: "+valutazioni[10]+"/5             ");
        stampaNota(valutazioni[11]);
        System.out.print("  Valutazione finale: "+valutazioni[12]+"/5   ");
        stampaNota(valutazioni[13]);

        System.out.print("\n  Per tornare indietro inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine();
    }

    /**
     * Stampa la nota delle valutazioni
     *
     * @param nota rappresenta la nota
     */

    private static void stampaNota(String nota) {

        int j=0;

        if(nota.length()<50) {
            System.out.print(nota);
        }

        else {

            for(int i=0;i<=nota.length()/50;i++) {
                if(i==nota.length()/50) {
                    System.out.print(nota.substring(j,nota.length()%50+(i)*50));
                    System.out.print("\n");
                }

                else {
                    System.out.print(nota.substring(j,(i+1)*50));
                    System.out.print("\n                            ");
                }

                j+=50;

            }
        }

        System.out.print("\n\n");
    }

}
