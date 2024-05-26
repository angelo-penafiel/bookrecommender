
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia.menu;

import bookrecommender.interfaccia.ricercalibro.RicercaLibroMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import java.util.Scanner;

public final class SceltaMenuMessaggi {

    private SceltaMenuMessaggi() {

    }

    public static void inserimentoSceltaMenuMessaggi() {
        System.out.print(" Scegli la voce del menu -> ");
    }

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

    public static void erroreScelte(int scelte) {
        System.out.println(" Errore! La voce deve essere tra 1 e "+scelte+"\n");
    }

    public static void erroreStringa() {
        System.out.println(" Errore! Inserire un numero!\n");
    }

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
                    RicercaLibroMessaggi.erroreScelte(
                        paginaCorrente*RicercaLibro.MAX_RISULTATI_PAGINA+1,max);
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
                    RicercaLibroMessaggi.erroreStringa();
                }
            }

        } while(!controllo);

        return scelta;
    }

}
