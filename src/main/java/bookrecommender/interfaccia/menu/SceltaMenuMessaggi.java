package bookrecommender.interfaccia.menu;

import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.util.Scanner;

/**
 * Classe che ha la funzione di stampare i
 * messaggi e gestire gli inserimenti delle
 * scelte dei menu.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class SceltaMenuMessaggi {

    //COSTRUTTORE

    private SceltaMenuMessaggi() {

    }

    //METODI

    /**
     * Stampa il messaggio di inserimento della
     * scelta di menu.
     */

    public static void inserimentoSceltaMenuMessaggi() {
        System.out.print(" Scegli la voce del menu -> ");
    }

    /**
     * Stampa il messaggio di inserimento della
     * scelta di menu diviso in pagine.
     */

    public static void inserimentoSceltaOpzioniPaginaMessaggi(int paginaCorrente, int pagineTotali) {

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

    /**
     * Stampa il messaggio di errore della
     * scelta di menu nel caso sia minore
     * o maggiore dei valori consentiti.
     */

    public static void erroreScelte(int scelte) {
        System.out.println(" Errore! La voce deve essere tra 1 e "+scelte+"\n");
    }

    /**
     * Stampa il messaggio di errore della
     * scelta di menu nel caso non sia un
     * numero.
     */

    public static void erroreStringa() {
        System.out.println(" Errore! Inserire un numero!\n");
    }

    /**
     * Gestisce l'inserimento di una scelta
     * del menu. Restituisce la scelta
     * inserita dall'utente.
     *
     * @param scelte rappresenta il numero di
     *               scelte
     * @return scelta dell'utente
     */

    public static int inserimentoSceltaMenu(int scelte) {

        var scelta = 0;
        String voce;
        boolean controllo;
        var in=new Scanner(System.in);

        do {
            controllo=true;

            SceltaMenuMessaggi.inserimentoSceltaMenuMessaggi();
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
                scelta=Integer.parseInt(voce);
                if(scelta<1||scelta>scelte) {
                    controllo=false;
                    SceltaMenuMessaggi.erroreScelte(scelte);
                }
            }

            else {
                SceltaMenuMessaggi.erroreStringa();
            }

        } while(!controllo);

        return scelta;
    }

    /**
     * Gestisce l'inserimento di una scelta
     * del menu diviso in pagine. Restituisce
     * la scelta inserita dall'utente.
     *
     * @param paginaCorrente rappresenta l'indice della
     *                       pagina corrente
     *
     * @param pagineTotali rappresenta il numero delle
     *                     pagine totali
     *
     * @param opzioniTotali rappresenta il numero massimo
     *                      dell'opzione da scegliere
     *
     * @return scelta dell'utente
     */

    public static int inserimentoSceltaOpzioniPagina(int paginaCorrente, int pagineTotali, int opzioniTotali) {

        var scelta = 0;
        String voce;
        boolean controllo;
        var in=new Scanner(System.in);

        do {
            controllo=true;

            inserimentoSceltaOpzioniPaginaMessaggi(paginaCorrente,pagineTotali);
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

                scelta=Integer.parseInt(voce);

                int max;

                if(paginaCorrente+1==pagineTotali) {
                    max=opzioniTotali;
                }

                else {
                    max=paginaCorrente* RicercaLibro.MAX_RISULTATI_PAGINA+RicercaLibro.MAX_RISULTATI_PAGINA;
                }

                if(scelta<paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA+1 ||
                    scelta>max) {
                    controllo=false;
                    erroreScelte(paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA+1,max);
                }
            }

            else {

                if(paginaCorrente+1<pagineTotali&&paginaCorrente==0) {
                    if ("d".equalsIgnoreCase(voce)) {
                        controllo=true;
                        scelta=-1;
                    }
                }

                if(paginaCorrente+1<pagineTotali&&paginaCorrente!=0) {
                    if ("d".equalsIgnoreCase(voce)||"a".equalsIgnoreCase(voce)) {
                        controllo=true;

                        if ("d".equalsIgnoreCase(voce)) {
                            scelta=-1;
                        }

                        if ("a".equalsIgnoreCase(voce)) {
                            scelta=-2;
                        }
                    }
                }

                if(paginaCorrente+1==pagineTotali) {
                    if ("a".equalsIgnoreCase(voce)) {
                        controllo=true;
                        scelta=-2;
                    }
                }

                if(!controllo) {
                    erroreStringaPagina();
                }
            }

        } while(!controllo);

        return scelta;
    }

    /**
     * Stampa il messaggio di errore delle scelte
     * del menu diviso in pagine.
     *
     * @param sceltaMinore rappresenta il numero della
     *                     scelta minima per pagina
     *
     * @param sceltaMaggiore rappresenta il numero
     *                       della scelta massima per
     *                       pagina
     */

    public static void erroreScelte(int sceltaMinore, int sceltaMaggiore) {
        System.out.println("      Errore! La voce deve essere tra "+sceltaMinore+" e "+sceltaMaggiore+"\n");
    }

    /**
     * Stampa il messaggio di errore della
     * scelta di menu diviso in pagine nel
     * caso non sia un numero.
     */

    public static void erroreStringaPagina() {
        System.out.println("      Errore!\n");
    }

}
