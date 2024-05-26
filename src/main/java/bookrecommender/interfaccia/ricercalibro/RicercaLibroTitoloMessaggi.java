
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.util.List;
import java.util.Scanner;

public final class RicercaLibroTitoloMessaggi {

    private RicercaLibroTitoloMessaggi() {

    }

    public static void intestazioneInserimentoTitolo() {
        System.out.println("---------------------------------Titolo-----------------------------------");
    }

    public static void inserimentoTitoloMessaggi() {
        System.out.print("| Inserisci il titolo del libro da cercare -> ");
    }

    private static void intestazioneTitolo() {

        System.out.print("         Titolo");

        for (int i=0;i<96;i++) {
            System.out.print(" ");
        }

        System.out.print("Anno pubblicazione\n");

        for(int cont=0;cont<150;cont++) {
            System.out.print("-");
        }

        System.out.print("\n");
    }

    public static void stampaOpzioniTitoloPagina(List<List<Object>> opzioniTitolo, int paginaCorrente) {

        int i=0;

        intestazioneTitolo();

        for(List<Object> opzioneTitolo:opzioniTitolo) {

            if(i>=paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA &&
                    i<(paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA)+RicercaLibro.MAX_RISULTATI_PAGINA) {

                int cont=0;

                String opzione = "";
                Integer annoPubblicazione = 0;

                for(Object oggetto:opzioneTitolo) {

                    if(cont==0) {
                        opzione= (String) oggetto;
                    }

                    if(cont==1) {
                        annoPubblicazione= (Integer) oggetto;
                    }

                    cont++;
                }

                stampaOpzioneTitolo(opzione,annoPubblicazione,i);

            }

            i++;
        }

        int pagina=paginaCorrente+1;
        int pagineTotali=opzioniTitolo.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1;

        System.out.print("\n         "+pagina+" di "+pagineTotali+" pagine\n\n");

    }

    public static void stampaOpzioniTitolo(List<List<Object>> opzioniTitolo) {

        int i=0;

        intestazioneTitolo();

        for(List<Object> opzioneTitolo:opzioniTitolo) {
            
            int cont=0;
            
            String opzione = "";
            Integer annoPubblicazione = 0;
            
            for(Object oggetto:opzioneTitolo) {
                
                if(cont==0) {
                    opzione= (String) oggetto;
                }
                
                if(cont==1) {
                    annoPubblicazione= (Integer) oggetto;
                }
                
                cont++;
            }

            stampaOpzioneTitolo(opzione,annoPubblicazione,i);

            i++;
        }

        System.out.print("\n");

    }

    public static void stampaOpzioneTitolo(String opzione, Integer annoPublicazione, int i) {

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

        System.out.print(j+") ");

        if(opzione.length()>97) {
            System.out.print(opzione.substring(0,1).toUpperCase()
                +opzione.substring(1,97)+"...  ("+annoPublicazione+")");
        }

        else {
            System.out.print(opzione.substring(0,1).toUpperCase()
                    +opzione.substring(1));
            for(int cont=0;cont<100-opzione.length();cont++) {
                System.out.print(" ");
            }
            System.out.print("  ("+annoPublicazione+")");
        }

        System.out.print("\n");

        for(int k=0;k<150;k++) {
            System.out.print("-");
        }

        System.out.print("\n");

    }

    public static void erroreInserimentoTitolo() {
        System.out.println("  Errore! Il titolo deve essere tra" +
                " "+ Libro.MIN_TITOLO+" e "+ Libro.MAX_TITOLO+" caratteri\n");
    }

    public static String inserimentoTitolo() {

        String titolo;
        boolean controllo;
        Scanner in=new Scanner(System.in);

        do {

            controllo=true;
            RicercaLibroTitoloMessaggi.inserimentoTitoloMessaggi();
            titolo=in.nextLine();

            if(titolo.length()<Libro.MIN_TITOLO||titolo.length()>Libro.MAX_TITOLO) {
                controllo=false;
                RicercaLibroTitoloMessaggi.erroreInserimentoTitolo();
            }

        } while(!controllo);

        titolo=titolo.toLowerCase();

        return titolo;
    }

}
