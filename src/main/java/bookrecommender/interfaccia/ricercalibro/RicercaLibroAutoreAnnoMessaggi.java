package bookrecommender.interfaccia.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.util.List;
import java.util.Scanner;

/**
 * Classe che ha la funzione di stampare i
 * messaggi e gestire gli inserimenti della
 * sezione di ricerca libro per autore e anno
 * di pubblicazione.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class RicercaLibroAutoreAnnoMessaggi {

    //COSTRUTTORE

    private RicercaLibroAutoreAnnoMessaggi() {

    }

    //METODI

    /**
     * Stampa l'intestazione della sezione di
     * inserimento dell'autore e dell'anno di
     * pubblicazione.
     */

    public static void intestazioneInserimentoAutoreAnno() {
        System.out.println("------------------------------Autore e anno-------------------------------");
    }

    /**
     * Stampa il messaggio d'inserimento dell'
     * anno di pubblicazione.
     */

    public static void inserimentoAnnoPubblicazioneMessaggi() {
        System.out.print("| Inserisci l'anno di pubblicazione del libro da cercare -> ");
    }

    /**
     * Stampa l'intestazione della stampa di
     * opzioni di autore e anno di pubblicazione.
     */

    private static void intestazioneAutoreAnno() {

        System.out.print("         Autore");

        for (int i=0;i<44;i++) {
            System.out.print(" ");
        }

        System.out.print("  Anno pubblicazione  Titolo\n");

        for(int cont=0;cont<110;cont++) {
            System.out.print("-");
        }

        System.out.print("\n");
    }

    /**
     * Stampa le opzioni di autore e anno di
     * pubblicazione divise per pagina.
     *
     * @param opzioniAutoreTitolo rappresenta una
     *                            lista di oggetti
     *                            di tipo libro
     *                            contenenti solamen-
     *                            -te autore e titolo
     *
     * @param opzioneAnno rappresenta l'anno di
     *                    pubblicazione
     *
     * @param paginaCorrente rappresenta la pagina
     *      *                corrente
     */

    public static void stampaOpzioniAutoreAnnoPagina(List<Libro> opzioniAutoreTitolo, Integer opzioneAnno,
        int paginaCorrente) {

        int i=0;

        intestazioneAutoreAnno();

        for(Libro opzioneAutoreTitolo:opzioniAutoreTitolo) {

            if(i>=paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA &&
                    i<(paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA)+RicercaLibro.MAX_RISULTATI_PAGINA) {
                stampaOpzioneAutoreAnno(opzioneAutoreTitolo.getAutori().get(0),
                    opzioneAnno,opzioneAutoreTitolo.getTitolo(),i);
            }

            i++;
        }

        int pagina=paginaCorrente+1;
        int pagineTotali=opzioniAutoreTitolo.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1;

        System.out.print("\n         "+pagina+" di "+pagineTotali+" pagine\n\n");

    }

    /**
     * Stampa le opzioni di autore e anno di
     * pubblicazione.
     *
     * @param opzioniAutoreTitolo rappresenta una
     *                            lista di oggetti
     *                            di tipo libro
     *                            contenenti solamen-
     *                            -te autore e titolo
     *
     * @param opzioneAnno rappresenta l'anno di
     *                    pubblicazione
     */

    public static void stampaOpzioniAutoreAnno(List<Libro> opzioniAutoreTitolo, Integer opzioneAnno) {

        int i=0;

        intestazioneAutoreAnno();

        for(Libro opzioneAutoreTitolo:opzioniAutoreTitolo) {

            stampaOpzioneAutoreAnno(opzioneAutoreTitolo.getAutori().get(0),opzioneAnno,
                opzioneAutoreTitolo.getTitolo(),i);

            i++;
        }

        System.out.print("\n");

    }

    /**
     * Stampa l'opzione singola di autore e
     * anno di pubblicazione.
     *
     * @param autore rappresenta l'autore
     *
     * @param annoPublicazione rappresenta
     *                         l'anno di
     *                         pubblicazione
     *
     * @param titolo rappresenta il titolo
     *
     * @param i rappresenta l'indice dell'
     *          opzione
     */

    public static void stampaOpzioneAutoreAnno(String autore, Integer annoPublicazione, String titolo,
        int i) {

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

        if(autore.length()>47) {
            System.out.print(autore.substring(0,1).toUpperCase()
                +autore.substring(1,47)+"...  ("+annoPublicazione+")");
        }

        else {
            System.out.print(autore.substring(0,1).toUpperCase()
                    +autore.substring(1));
            for(int cont=0;cont<50-autore.length();cont++) {
                System.out.print(" ");
            }
            System.out.print("  ("+annoPublicazione+")");
        }

        for(int cont=0;cont<14;cont++) {
            System.out.print(" ");
        }

        if(titolo.length()>22) {
            System.out.print(titolo.substring(0,1).toUpperCase()
                +titolo.substring(1,22)+"...");
        }

        else {
            System.out.print(titolo.substring(0,1).toUpperCase()
                +titolo.substring(1));
        }

        System.out.print("\n");

        for(int k=0;k<110;k++) {
            System.out.print("-");
        }

        System.out.print("\n");
    }

    /**
     * Stampa il messaggio di errore di
     * inserimento dell'anno di pubblicazione.
     */

    public static void erroreInserimentoAnnoPubblicazione() {
        System.out.println("  Errore! L'anno deve essere tra "+Libro.MIN_ANNO_PUBBLICAZIONE
                +" e "+Libro.MAX_ANNO_PUBBLICAZIONE+"\n");
    }

    /**
     * Stampa il messaggio di libro non
     * trovato in base alla ricerca per
     * autore e anno di pubblicazione.
     */

    public static void valoriNonTrovati() {
        System.out.print("\n  Non sono presenti libri dall'autore e anno cercato! Inserisci qualsiasi tasto: ");
        Scanner in=new Scanner(System.in);
        in.nextLine();
    }

    /**
     * Gestisce l'inserimento dell'anno di
     * pubblicazione.
     *
     * @return  l'anno di pubblicazione
     *          inserito dall'utente
     */

    public static int inserimentoAnnoPubblicazione() {

        int annoPubblicazione = 0;
        boolean controllo;
        String voce;
        Scanner in=new Scanner(System.in);

        do {

            controllo=true;
            RicercaLibroAutoreAnnoMessaggi.inserimentoAnnoPubblicazioneMessaggi();
            voce=in.nextLine();

            for(var i=0;i<voce.length();i++) {
                if(!Character.isDigit(voce.charAt(i))) {
                    controllo=false;
                }
            }

            if(voce.isEmpty()) {
                controllo=false;
            }

            if(controllo) {
                annoPubblicazione=Integer.parseInt(voce);
                if(annoPubblicazione<Libro.MIN_ANNO_PUBBLICAZIONE||
                    annoPubblicazione>Libro.MAX_ANNO_PUBBLICAZIONE) {
                    controllo=false;
                    RicercaLibroAutoreAnnoMessaggi.erroreInserimentoAnnoPubblicazione();
                }
            }

            else {
                SceltaMenuMessaggi.erroreStringa();
            }

        } while(!controllo);

        return annoPubblicazione;
    }

}
