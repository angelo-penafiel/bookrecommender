package bookrecommender.interfaccia.visualizzazioneLibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.Valutazione;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Classe che ha la funzione di stampare i
 * messaggi della sezione di visualizzazione
 * del libro.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class VisualizzazioneLIbroMessaggi {

    //COSTRUTTORE

    private VisualizzazioneLIbroMessaggi() {

    }

    //METODI

    /**
     * Stampa l'intestazione della sezione.
     */

    public static void intestazione() {
        System.out.println("---------------------------------Libro------------------------------------");
    }

    /**
     * Stampa l'intestazione della parte
     * di valutazione.
     */

    public static void intestazioneValutazione() {
        System.out.print("\n\n------------------------------Valutazione------------------------------");
    }

    /**
     * Stampa l'intestazione della parte
     * dei libri consigliati.
     */

    public static void intestazioneConsigliati() {
        System.out.print("\n\n------------------------------Consigliati------------------------------");
    }

    /**
     * Stampa il menu di scelta nel caso in cui
     * l'utente non è registrato.
     */

    public static void menuSceltaSenzaRegistrazione() {
        System.out.println("----------------------------------------------------------");
        System.out.println("| 1) Torna al menu di scelta della modalità di ricerca ");
        System.out.println("| 2) Torna al menu principale ");
        System.out.println("| 3) Esci dal programma");
        System.out.println("----------------------------------------------------------");
    }

    /**
     * Stampa il menu di scelta nel caso in cui
     * l'utente è registrato e si trova nella
     * sezione di ricerca del libro.
     */

    public static void menuSceltaUtenteRegistrato() {
        System.out.println("----------------------------------------------------------");
        System.out.println("| 1) Torna al menu di scelta della modalità di ricerca ");
        System.out.println("| 2) Torna al menu principale ");
        System.out.println("| 3) Log out");
        System.out.println("| 4) Esci dal programma");
        System.out.println("----------------------------------------------------------");
    }

    /**
     * Stampa il messaggio nel caso in cui
     * l'utente è registrato e si trova nella
     * sezione di visualizzazione di consigliati.
     */

    public static void consigliati() {
        System.out.print("\n  Inserisci qualsiasi tasto per tornare indietro: ");
        Scanner in=new Scanner(System.in);
        in.nextLine(  );
    }

    /**
     * Stampa i dati del libro dati il libro
     * corrente, l'hashmap del titolo dei
     * consigliati e il numero di utenti, la
     * valutazione media e il numero di valutazioni.
     *
     * @param libro indica il libro corrente
     *
     * @param consigliatiCounted indica l'hashmap del titolo
     *                           dei consigliati e il
     *                           numero di utenti
     *
     * @param media indica la valutazione media
     *
     * @param numeroValutazioni indica il numero di
     *                          valutazioni
     */

    public static void stampaDatiLibro(Libro libro, HashMap<String, Integer> consigliatiCounted,
        Valutazione media, int numeroValutazioni) {

        System.out.print("\n  Titolo: ");
        stampaTìtolo(libro.getTitolo());

        System.out.print("  Autori: ");
        stampaAutori(libro.getAutori());

        System.out.print("\n\n  Anno di pubblicazione: "+libro.getAnnoPubblicazione());

        System.out.print("\n\n  Editore: "+libro.getEditore().substring(0,1).toUpperCase()
            +libro.getEditore().substring(1));

        System.out.print("\n\n  Categorie: ");
        stampaCategorie(libro.getCategorie());

        intestazioneConsigliati();
        stampaConsigliati(consigliatiCounted);

        intestazioneValutazione();
        stampaValutazione(media,numeroValutazioni);

        System.out.print("\n\n");

    }

    /**
     * Stampa il titolo del libro
     * corrente dato il titolo.
     *
     * @param titolo indica il titolo
     */

    private static void stampaTìtolo(String titolo) {

        titolo=titolo.substring(0,1).toUpperCase()+titolo.substring(1);

        int j=0;

        if(titolo.length()<50) {
            System.out.print(titolo);
        }

        else {

            for(int i=0;i<=titolo.length()/50;i++) {
                if(i==titolo.length()/50) {
                    System.out.print(titolo.substring(j,titolo.length()%50+(i)*50));
                }

                else {
                    System.out.print(titolo.substring(j,(i+1)*50));
                    System.out.print("\n          ");
                }

                j+=50;

            }
        }

        System.out.print("\n\n");
    }

    /**
     * Stampa gli autori del libro
     * corrente data la lista di autori.
     *
     * @param autori indica la lista di autori
     */

    private static void stampaAutori(List<String> autori)  {

        for(int i=0;i<autori.size();i++) {
            System.out.print(autori.get(i).substring(0,1).toUpperCase()
                +autori.get(i).substring(1));

            if(i+1==autori.size()) {
                System.out.print(".");
            }

            else {
                System.out.print(", ");
            }

        }
    }

    /**
     * Stampa le categorie del libro
     * corrente data la lista di categorie.
     *
     * @param categorie indica la lista di categorie
     */

    private static void stampaCategorie(List<String> categorie)  {

        for(int i=0;i<categorie.size();i++) {
            System.out.print(categorie.get(i).substring(0,1).toUpperCase()+
                categorie.get(i).substring(1));

            if(i+1==categorie.size()) {
                System.out.print(".");
            }

            else {
                System.out.print(", ");
            }

        }
    }

    /**
     * Stampa i libri consigliati del libro
     * corrente dato l'hashmap del titolo dei
     * consigliati e il numero di utenti.
     *
     * @param consigliatiCounted indica l'hashmap del titolo
     *                           dei consigliati e il
     *                           numero di utenti
     */

    private static void stampaConsigliati(HashMap<String, Integer> consigliatiCounted) {

        if(consigliatiCounted.size()==0) {
            System.out.print("\n\n  Non ci sono libri consigliati.");
        }

        else {

            for (Entry<String, Integer> entry : consigliatiCounted.entrySet()) {

                System.out.print("\n\n  ");

                if(entry.getKey().length()>16) {
                    System.out.print(entry.getKey().substring(0,1).toUpperCase()
                        +entry.getKey().substring(1,16)+"...");
                }

                else {
                    System.out.print(entry.getKey().substring(0,1).toUpperCase()
                        +entry.getKey().substring(1));
                }

                System.out.print(" è stato consigliato da "+entry.getValue()+" utenti");
            }
        }
    }

    /**
     * Stampa la valutazione del libro
     * corrente data la valutazione media e
     * il numero di valutazioni.
     *
     * @param media indica la valutazione media
     *
     * @param numeroValutazioni indica il numero di
     *                          valutazioni
     */

    private static void stampaValutazione(Valutazione media, int numeroValutazioni) {

        if(media.getStile()==0&&media.getContenuto()==0&&media.getGradevolezza()==0
            &&media.getOriginalita()==0&&media.getEdizione()==0&&media.getFinale()==0) {

            System.out.print("\n\n  Non ci sono valutazioni per questo libro.");
        }

        else {
            System.out.print("\n\n  Stile: "+media.getStile()+"/5");
            System.out.print("\n\n  Contenuto: "+media.getContenuto()+"/5");
            System.out.print("\n\n  Gradevolezza: "+media.getGradevolezza()+"/5");
            System.out.print("\n\n  Originalità: "+media.getOriginalita()+"/5");
            System.out.print("\n\n  Edizione: "+media.getEdizione()+"/5");
            System.out.print("\n\n  Finale: "+media.getFinale()+"/5");
            System.out.print("\n\n  "+numeroValutazioni+" utenti hanno valutato il libro.");
        }

    }

}
