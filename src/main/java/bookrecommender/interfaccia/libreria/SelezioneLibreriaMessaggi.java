package bookrecommender.interfaccia.libreria;

import bookrecommender.struttura.libreria.SelezioneLibreria;
import java.util.List;

/**
 * Classe che ha la funzione di stampare i
 * messaggi e gestire gli inserimenti della
 * sezione di selezione di una libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class SelezioneLibreriaMessaggi {

    //COSTRUTTORE

    private SelezioneLibreriaMessaggi() {

    }

    //METODI


    /**
     * Stampa l'intestazione della stampa di
     * opzioni di nome.
     */

    private static void intestazioneNomi() {

        System.out.print("         Nome\n");

        for(int cont=0;cont<110;cont++) {
            System.out.print("-");
        }

        System.out.print("\n");
    }

    /**
     * Stampa le opzioni di nome divise per pagina.
     *
     * @param opzioniNome rappresenta una lista di
     *                    nomi
     *
     * @param paginaCorrente rappresenta la pagina
     *      *                corrente
     */

    public static void stampaOpzioniNomePagina(List<String> opzioniNome, int paginaCorrente) {

        int i=0;

        intestazioneNomi();

        for(String opzioneNome:opzioniNome) {

            if(i>=paginaCorrente* SelezioneLibreria.MAX_RISULTATI_PAGINA &&
                i<(paginaCorrente*SelezioneLibreria.MAX_RISULTATI_PAGINA)+SelezioneLibreria.MAX_RISULTATI_PAGINA) {

                stampaOpzioneNome(opzioneNome,i);
            }

            i++;
        }

        int pagina=paginaCorrente+1;
        int pagineTotali=opzioniNome.size()/SelezioneLibreria.MAX_RISULTATI_PAGINA+1;

        System.out.print("\n         "+pagina+" di "+pagineTotali+" pagine\n\n");

    }

    /**
     * Stampa le opzioni di nome.
     *
     * @param opzioniNome rappresenta una lista
     *                    di nomi
     */

    public static void stampaOpzioniNome(List<String> opzioniNome) {

        int i=0;

        intestazioneNomi();

        for(String opzioneNome:opzioniNome) {

            stampaOpzioneNome(opzioneNome,i);

            i++;
        }

        System.out.print("\n");

    }

    /**
     * Stampa l'opzione singola di nome.
     *
     * @param nome rappresenta il nome
     *
     * @param i rappresenta l'indice dell'
     *          opzione
     */

    public static void stampaOpzioneNome(String nome, int i) {

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

        if(nome.length()>97) {
            System.out.print(nome.substring(0,1).toUpperCase()
                +nome.substring(1,97)+"...");
        }

        else {
            System.out.print(nome.substring(0,1).toUpperCase()
                +nome.substring(1));
        }

        System.out.print("\n");

        for(int k=0;k<110;k++) {
            System.out.print("-");
        }

        System.out.print("\n");

    }

}
