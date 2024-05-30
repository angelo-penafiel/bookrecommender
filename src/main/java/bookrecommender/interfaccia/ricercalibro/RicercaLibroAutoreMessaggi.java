package bookrecommender.interfaccia.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.util.List;
import java.util.Scanner;

/**
 * Classe che ha la funzione di stampare i
 * messaggi e gestire gli inserimenti della
 * sezione di ricerca libro per autore.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class RicercaLibroAutoreMessaggi {

    //COSTRUTTORE

    private RicercaLibroAutoreMessaggi() {

    }

    //METODI

    /**
     * Stampa l'intestazione della sezione di
     * inserimento dell'autore.
     */

    public static void intestazioneInserimentoAutore() {
        System.out.println("---------------------------------Autore-----------------------------------");
    }

    /**
     * Stampa il messaggio d'inserimento dell'
     * autore.
     */

    public static void inserimentoAutoreMessaggi() {
        System.out.print("| Inserisci l'autore del libro da cercare -> ");
    }

    /**
     * Stampa l'intestazione della stampa di
     * opzioni di autore.
     */

    private static void intestazioneAutore() {

        System.out.print("         Autore\n");

        for(int cont=0;cont<60;cont++) {
            System.out.print("-");
        }

        System.out.print("\n");
    }

    /**
     * Stampa le opzioni di autore divise per pagina.
     *
     * @param opzioniAutore rappresenta una lista di
     *                      autori
     *
     * @param paginaCorrente rappresenta la pagina
     *      *                corrente
     */

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

    /**
     * Stampa le opzioni di autore.
     *
     * @param opzioniAutore rappresenta una lista di
     *                      autori
     */

    public static void stampaOpzioniAutore(List<String> opzioniAutore) {

        int i=0;

        intestazioneAutore();

        for(String opzioneAutore:opzioniAutore) {

            stampaOpzioneAutore(opzioneAutore,i);

            i++;
        }

        System.out.print("\n");

    }

    /**
     * Stampa l'opzione singola di autore.
     *
     * @param autore rappresenta l'autore
     *
     * @param i rappresenta l'indice dell'
     *          opzione
     */

    public static void stampaOpzioneAutore(String autore, int i) {

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

        System.out.print(j+") "+autore+"\n");

        for(int k=0;k<60;k++) {
            System.out.print("-");
        }

        System.out.print("\n");
    }

    /**
     * Stampa il messaggio di errore di
     * inserimento dell'autore.
     */

    public static void erroreInserimentoAutore() {
        System.out.println("  Errore! L'autore deve essere tra" +
                " "+ Libro.MIN_AUTORE_CARATTERI+" e "+ Libro.MAX_AUTORE_CARATTERI+" caratteri\n");
    }

    /**
     * Stampa il messaggio di libro non
     * trovato in base alla ricerca per
     * autore.
     */

    public static void valoriNonTrovati() {
        System.out.print("\n  Non sono presenti libri dall'autore cercato! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine();
    }

    /**
     * Gestisce l'inserimento dell'autore.
     *
     * @return autore inserito dall'utente
     */

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
