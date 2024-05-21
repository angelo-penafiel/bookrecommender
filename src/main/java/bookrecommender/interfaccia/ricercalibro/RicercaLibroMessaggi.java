
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.sezioni.ricercalibro.RicercaLibro;

import java.util.List;

public final class RicercaLibroMessaggi {

    private RicercaLibroMessaggi() {

    }

    public static void menu(String username) {

        System.out.println("-----Menu principale/Utente: "+username+"-----");
        System.out.println("| 1) Luci ");
        System.out.println("| 2) Lavatrici ");
        System.out.println("| 3) Notifiche ");
        System.out.println("| 4) Log out ");
        System.out.println("| 5) Esci dal programma ");
        System.out.println("----------------------------------------------------");
    }

    public static void menuSenzaRegistrazione() {

        System.out.println("----------------Ricerca libro-----------------");
        System.out.println("| 1) Cerca libro per titolo                  |");
        System.out.println("| 2) Cerca libro per autore                  |");
        System.out.println("| 3) Cerca libro per autore e anno pubbli.   |");
        System.out.println("| 4) Accedi                                  |");
        System.out.println("| 5) Registrati                              |");
        System.out.println("| 6) Esci dal programma                      |");
        System.out.println("----------------------------------------------");
    }

    public static void intestazioneInserimentoTitolo() {
        System.out.println("---------------------------------Titolo-----------------------------------");
    }

    public static void intestazioneInserimentoAutore() {
        System.out.println("---------------------------------Autore-----------------------------------");
    }

    public static void inserimentoTitolo() {
        System.out.print("| Inserisci il titolo del libro da cercare -> ");
    }

    public static void inserimentoAutore() {
        System.out.print("| Inserisci l'autore del libro da cercare -> ");
    }

    public static void inserimentoAnnoPubblicazione() {
        System.out.print("| Inserisci l'anno di pubblicazione del libro da cercare -> ");
    }

    public static void inserimentoScelta(int paginaCorrente, int pagineTotali) {

        if(paginaCorrente+1<pagineTotali&&paginaCorrente==0) {
            System.out.print("      Scegli la voce del menu o inserisci 'd' per cambiare pagina -> ");
        }

        if(paginaCorrente+1<pagineTotali&&paginaCorrente!=0) {
            System.out.print("      Scegli la voce del menu o inserisci 'a' o 'd' per cambiare pagina -> ");
        }

        if(paginaCorrente+1==pagineTotali) {
            System.out.print("      Scegli la voce del menu o inserisci 'a' per cambiare pagina -> ");
        }

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

        if(opzione.length()>97) System.out.print(opzione.substring(0,1).toUpperCase()
                +opzione.substring(1,97)+"...  ("+annoPublicazione+")");

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

    private static void intestazioneAutoreAnno() {

        System.out.print("         Autore");

        for (int i=0;i<44;i++) {
            System.out.print(" ");
        }

        System.out.print("  Anno pubblicazione\n");

        for(int cont=0;cont<80;cont++) {
            System.out.print("-");
        }

        System.out.print("\n");
    }

    public static void stampaOpzioniAutoreAnnoPagina(List<String> opzioniAutore, List<Integer> opzioniAnno,
                                                     int paginaCorrente) {

        int i=0;

        intestazioneAutoreAnno();

        for(String opzioneAutore:opzioniAutore) {

            if(i>=paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA &&
                    i<(paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA)+RicercaLibro.MAX_RISULTATI_PAGINA) {
                stampaOpzioneAutoreAnno(opzioneAutore,opzioniAnno.get(i),i);
            }

            i++;
        }

        int pagina=paginaCorrente+1;
        int pagineTotali=opzioniAutore.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1;

        System.out.print("\n         "+pagina+" di "+pagineTotali+" pagine\n\n");

    }

    public static void stampaOpzioniAutoreAnno(List<String> opzioniAutore, List<Integer> opzioniAnno) {

        int i=0;

        intestazioneAutoreAnno();

        for(String opzioneAutore:opzioniAutore) {

            stampaOpzioneAutoreAnno(opzioneAutore,opzioniAnno.get(i),i);

            i++;
        }

        System.out.print("\n");

    }

    public static void stampaOpzioneAutoreAnno(String autore, Integer annoPublicazione, int i) {

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


        if(autore.length()>47) System.out.print(autore.substring(0,1).toUpperCase()
                +autore.substring(1,47)+"...  ("+annoPublicazione+")");

        else {
            System.out.print(autore.substring(0,1).toUpperCase()
                    +autore.substring(1));
            for(int cont=0;cont<50-autore.length();cont++) {
                System.out.print(" ");
            }
            System.out.print("  ("+annoPublicazione+")");
        }

        System.out.print("\n");

        for(int k=0;k<80;k++) {
            System.out.print("-");
        }

        System.out.print("\n");
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

    public static void erroreInserimentoTitolo() {
        System.out.println("  Errore! Il titolo deve essere tra" +
                " "+ Libro.MIN_TITOLO+" e "+ Libro.MAX_TITOLO+" caratteri\n");
    }

    public static void erroreInserimentoAutore() {
        System.out.println("  Errore! L'autore deve essere tra" +
                " "+ Libro.MIN_AUTORE_CARATTERI+" e "+ Libro.MAX_AUTORE_CARATTERI+" caratteri\n");
    }

    public static void erroreScelte(int sceltaMinore, int sceltaMaggiore) {
        System.out.println("      Errore! La voce deve essere tra "+sceltaMinore+" e "+sceltaMaggiore+"\n");
    }

    public static void erroreStringa() {
        System.out.println("      Errore!\n");
    }

    public static void erroreInserimentoAnnoPubblicazione(int annoCorrente) {
        System.out.println("  Errore! L'anno deve essere tra "+Libro.MIN_ANNO_PUBBLICAZIONE
                +" e "+annoCorrente+"\n");
    }


}
