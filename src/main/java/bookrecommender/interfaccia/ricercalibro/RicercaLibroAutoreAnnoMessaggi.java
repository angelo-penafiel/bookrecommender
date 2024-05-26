
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public final class RicercaLibroAutoreAnnoMessaggi {

    private RicercaLibroAutoreAnnoMessaggi() {

    }

    public static void intestazioneInserimentoAutore() {
        System.out.println("---------------------------------Autore-----------------------------------");
    }

    public static void inserimentoAnnoPubblicazioneMessaggi() {
        System.out.print("| Inserisci l'anno di pubblicazione del libro da cercare -> ");
    }

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

    public static void stampaOpzioniAutoreAnnoPagina(List<String> opzioniAutore, Integer opzioneAnno,
                                                     List<String> opzioniTitoli, int paginaCorrente) {

        int i=0;

        intestazioneAutoreAnno();

        for(String opzioneAutore:opzioniAutore) {

            if(i>=paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA &&
                    i<(paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA)+RicercaLibro.MAX_RISULTATI_PAGINA) {
                stampaOpzioneAutoreAnno(opzioneAutore,opzioneAnno,opzioniTitoli.get(i),i);
            }

            i++;
        }

        int pagina=paginaCorrente+1;
        int pagineTotali=opzioniAutore.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1;

        System.out.print("\n         "+pagina+" di "+pagineTotali+" pagine\n\n");

    }

    public static void stampaOpzioniAutoreAnno(List<String> opzioniAutore, Integer opzioneAnno,
        List<String> opzioniTitoli) {

        int i=0;

        intestazioneAutoreAnno();

        for(String opzioneAutore:opzioniAutore) {

            stampaOpzioneAutoreAnno(opzioneAutore,opzioneAnno,opzioniTitoli.get(i),i);

            i++;
        }

        System.out.print("\n");

    }

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

    public static void erroreInserimentoAnnoPubblicazione(int annoCorrente) {
        System.out.println("  Errore! L'anno deve essere tra "+Libro.MIN_ANNO_PUBBLICAZIONE
                +" e "+annoCorrente+"\n");
    }

    public static int inserimentoAnnoPubblicazione() {

        int annoPubblicazione = 0;
        boolean controllo;
        String voce;
        Scanner in=new Scanner(System.in);

        LocalDateTime localDateTime=LocalDateTime.now();
        int annoCorrente=localDateTime.getYear();

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
                if(annoPubblicazione<Libro.MIN_ANNO_PUBBLICAZIONE||annoPubblicazione>annoCorrente) {
                    controllo=false;
                    RicercaLibroAutoreAnnoMessaggi.erroreInserimentoAnnoPubblicazione(annoCorrente);
                }
            }

            else {
                SceltaMenuMessaggi.erroreStringa();
            }

        } while(!controllo);

        return annoPubblicazione;
    }

}
