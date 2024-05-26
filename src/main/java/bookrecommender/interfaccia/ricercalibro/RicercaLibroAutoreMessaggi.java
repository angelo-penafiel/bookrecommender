
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.util.List;
import java.util.Scanner;

public final class RicercaLibroAutoreMessaggi {

    private RicercaLibroAutoreMessaggi() {

    }

    public static void intestazioneInserimentoAutore() {
        System.out.println("---------------------------------Autore-----------------------------------");
    }

    public static void inserimentoAutoreMessaggi() {
        System.out.print("| Inserisci l'autore del libro da cercare -> ");
    }

    private static void intestazioneAutore() {

        System.out.print("         Autore\n");

        for(int cont=0;cont<60;cont++) {
            System.out.print("-");
        }

        System.out.print("\n");
    }

    public static void stampaOpzioniAutorePagina(List<String> opzioniAutore, int paginaCorrente) {

        int i=0;

        intestazioneAutore();

        for(String opzioneAutore:opzioniAutore) {

            if(i>=paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA &&
                    i<(paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA)+RicercaLibro.MAX_RISULTATI_PAGINA) {
                stampaOpzioneAutore(opzioneAutore,i);
            }

            i++;
        }

        int pagina=paginaCorrente+1;
        int pagineTotali=opzioniAutore.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1;

        System.out.print("\n         "+pagina+" di "+pagineTotali+" pagine\n\n");

    }

    public static void stampaOpzioniAutore(List<String> opzioniAutore) {

        int i=0;

        intestazioneAutore();

        for(String opzioneAutore:opzioniAutore) {

            stampaOpzioneAutore(opzioneAutore,i);

            i++;
        }

        System.out.print("\n");

    }

    public static void stampaOpzioneAutore(String opzione, int i) {

        int j=i+1;

        System.out.print(" ");
        if(j<10) {
            System.out.print(" ");
        }
        if(j<100) {
            System.out.print(" ");
        }
        if(j<1000) {
            System.out.print(" ");
        }
        if(j<10000) {
            System.out.print(" ");
        }
        if(j<100_000) {
            System.out.print(" ");
        }

        System.out.print(j+") "+opzione+"\n");

        for(int k=0;k<60;k++) {
            System.out.print("-");
        }

        System.out.print("\n");
    }

    public static void erroreInserimentoAutore() {
        System.out.println("  Errore! L'autore deve essere tra" +
                " "+ Libro.MIN_AUTORE_CARATTERI+" e "+ Libro.MAX_AUTORE_CARATTERI+" caratteri\n");
    }

    public static String inserimentoAutore() {

        String autore;
        boolean controllo;
        Scanner in=new Scanner(System.in);

        do {

            controllo=true;
            RicercaLibroAutoreMessaggi.inserimentoAutoreMessaggi();
            autore=in.nextLine();

            if(!"".equals(autore)) {
                if(autore.length()<Libro.MIN_AUTORE_CARATTERI||autore.length()>Libro.MAX_AUTORE_CARATTERI) {
                    controllo=false;
                    RicercaLibroAutoreMessaggi.erroreInserimentoAutore();
                }
            }

        } while(!controllo);

        autore=autore.toLowerCase();

        return autore;
    }

}
