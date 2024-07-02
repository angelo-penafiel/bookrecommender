package bookrecommender.interfaccia.valutazione;

import java.util.Scanner;

public final class VisualizzaValutazioneMessaggi {

    //COSTRUTTORE

    private VisualizzaValutazioneMessaggi() {

    }

    //METODI

    public static void intestazione() {
        System.out.println("----------------------------------Valutazione-----------------------------------");
    }

    public static void valutazioni(String [] valutazioni) {

        System.out.print("\n                            Note\n\n");
        System.out.print("  Stile: "+valutazioni[2]+"/5                ");
        stampaNote(valutazioni[3]);
        System.out.print("  Contenuto: "+valutazioni[4]+"/5            ");
        stampaNote(valutazioni[5]);
        System.out.print("  Gradevolezza: "+valutazioni[6]+"/5         ");
        stampaNote(valutazioni[7]);
        System.out.print("  Originalità: "+valutazioni[8]+"/5          ");
        stampaNote(valutazioni[9]);
        System.out.print("  Edizione: "+valutazioni[10]+"/5             ");
        stampaNote(valutazioni[11]);
        System.out.print("  Valutazione finale: "+valutazioni[12]+"/5   ");
        stampaNote(valutazioni[13]);

        System.out.print("\n  Per tornare indietro inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine();
    }

    private static void stampaNote(String note) {

        int j=0;

        if(note.length()<50) {
            System.out.print(note);
        }

        else {

            for(int i=0;i<=note.length()/50;i++) {
                if(i==note.length()/50) {
                    System.out.print(note.substring(j,note.length()%50+(i)*50));
                    System.out.print("\n");
                }

                else {
                    System.out.print(note.substring(j,(i+1)*50));
                    System.out.print("\n                            ");
                }

                j+=50;

            }
        }

        System.out.print("\n\n");
    }

}
