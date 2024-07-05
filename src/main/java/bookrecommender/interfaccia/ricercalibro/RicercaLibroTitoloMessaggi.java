package bookrecommender.interfaccia.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.util.List;
import java.util.Scanner;

/**
 * Classe che ha la funzione di stampare i
 * messaggi e gestire gli inserimenti della
 * sezione di ricerca libro per titolo.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class RicercaLibroTitoloMessaggi {

    //COSTRUTTORE

    private RicercaLibroTitoloMessaggi() {

    }

    //METODI

    /**
     * Stampa l'intestazione della sezione di
     * inserimento del titolo.
     */

    public static void intestazioneInserimentoTitolo() {
        System.out.println("---------------------------------Titolo-----------------------------------");
    }

    /**
     * Stampa il messaggio d'inserimento del
     * titolo.
     */

    public static void inserimentoTitoloMessaggi() {
        System.out.print("| Inserisci il titolo del libro da cercare -> ");
    }

    /**
     * Stampa l'intestazione della stampa di
     * opzioni di titolo e anno di pubblicazione.
     */

    private static void intestazioneTitoloAnno() {

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

    /**
     * Stampa le opzioni di titolo e anno di
     * pubblicazione divise per pagina.
     *
     * @param opzioniTitoloAnno rappresenta una
     *                          lista di oggetti
     *                          di tipo libro
     *                          contenenti solamen-
     *                          -te titolo e anno
     *                          di pubblicazione
     *
     * @param paginaCorrente rappresenta la pagina
     *      *                corrente
     */

    public static void stampaOpzioniTitoloAnnoPagina(List<Libro> opzioniTitoloAnno, int paginaCorrente) {

        int i=0;

        intestazioneTitoloAnno();

        for(Libro opzioneTitoloAnno:opzioniTitoloAnno) {

            if(i>=paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA &&
                    i<(paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA)+RicercaLibro.MAX_RISULTATI_PAGINA) {

                stampaOpzioneTitoloAnno(opzioneTitoloAnno.getTitolo(),opzioneTitoloAnno.getAnnoPubblicazione(),i);
            }

            i++;
        }

        int pagina=paginaCorrente+1;
        int pagineTotali=opzioniTitoloAnno.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1;

        System.out.print("\n         "+pagina+" di "+pagineTotali+" pagine\n\n");

    }

    /**
     * Stampa le opzioni di titolo e anno di
     * pubblicazione.
     *
     * @param opzioniTitoloAnno rappresenta una
     *                          lista di oggetti
     *                          di tipo libro
     *                          contenenti solamen-
     *                          -te titolo e anno
     *                          di pubblicazione
     */

    public static void stampaOpzioniTitoloAnno(List<Libro> opzioniTitoloAnno) {

        int i=0;

        intestazioneTitoloAnno();

        for(Libro opzioneTitoloAnno:opzioniTitoloAnno) {

            stampaOpzioneTitoloAnno(opzioneTitoloAnno.getTitolo(),opzioneTitoloAnno.getAnnoPubblicazione(),i);

            i++;
        }

        System.out.print("\n");

    }

    /**
     * Stampa l'opzione singola di titolo e
     * anno di pubblicazione.
     *
     * @param titolo rappresenta il titolo
     *
     * @param annoPublicazione rappresenta
     *                         l'anno di
     *                         pubblicazione
     *
     * @param i rappresenta l'indice dell'
     *          opzione
     */

    public static void stampaOpzioneTitoloAnno(String titolo, Integer annoPublicazione, int i) {

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

        if(titolo.length()>97) {
            System.out.print(titolo.substring(0,1).toUpperCase()
                +titolo.substring(1,97)+"...  ("+annoPublicazione+")");
        }

        else {
            System.out.print(titolo.substring(0,1).toUpperCase()
                    +titolo.substring(1));
            for(int cont=0;cont<100-titolo.length();cont++) {
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

    /**
     * Stampa il messaggio di errore di
     * inserimento del titolo.
     */

    public static void erroreInserimentoTitolo() {
        System.out.println("  Errore! Il titolo deve essere tra" +
                " "+ Libro.MIN_TITOLO+" e "+ Libro.MAX_TITOLO+" caratteri\n");
    }

    /**
     * Stampa il messaggio di libro non
     * trovato in base alla ricerca per
     * titolo.
     */

    public static void valoriNonTrovati() {
        System.out.print("\n  Non sono presenti libri dal titolo cercato! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine();
    }

    /**
     * Gestisce l'inserimento del titolo.
     *
     * @return il titolo inserito dall'utente
     */

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
