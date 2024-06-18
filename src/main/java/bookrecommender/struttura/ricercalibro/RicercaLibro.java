package bookrecommender.struttura.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroMessaggi;
import bookrecommender.struttura.visualizzazionelibro.VisualizzazioneLibro;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Classe che ha la funzione di gestire la
 * sezione del menu di scelta della ricerca
 * dei libri.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class RicercaLibro {

    //CAMPI

    /**
     * Costante che indica il numero massimo
     * di risultati stampati per pagina.
     */

    public static final Integer MAX_RISULTATI_PAGINA=7;

    /**
     * Campo che indica il numero di scelta
     * effettuata del menu di ricerca dei
     * libri.
     */

    private int scelta;

    private Libro libro;


    //COSTRUTTORE

    /**
     * Gestisce l'indirizzamento del flusso del
     * programma in base alla scelta della modalità
     * di ricerca dei libri nel caso in cui l'utente
     * non ha effettuato il login.
     */

    public RicercaLibro(int menuProvenienza) {
        cercaLibro(menuProvenienza);
    }

    //METODI

    private void caricamentoLibri() throws IOException {

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
        "Publish Date (Month)","Publish Date (Year)"};

        var csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        for (CSVRecord record : records) {

            String titolo=record.get("Title").toLowerCase();
            String authors=record.get("Authors").toLowerCase();
            Integer annoPubblicazione=Integer.parseInt(record.get("Publish Date (Year)"));
            String editore=record.get("Publisher").toLowerCase();
            String categories=record.get("Category").toLowerCase();
           }
    }

    /**
     * Gestisce l'indirizzamento del flusso del
     * programma in base alla scelta della modalità
     * di ricerca dei libri.
     */

    private void cercaLibro(int menuProvenienza) {

        boolean controllo;

        do {

            controllo=true;

            if(menuProvenienza==0) {
                controllo=cercaLibroSenzaRegistrazione(controllo, menuProvenienza);
            }

            if(menuProvenienza==1) {
                controllo=cercaLibroUtenteRegistrato(controllo, menuProvenienza);
            }

            if(menuProvenienza==2) {
                controllo=cercaLibroUtenteRegistratoConsigli(controllo, menuProvenienza);
            }



        } while(!controllo);
    }

    private boolean cercaLibroSenzaRegistrazione(boolean controllo, int menuProvenienza) {

        NuovaSchermata.nuovaSchermata();
        RicercaLibroMessaggi.menuSenzaRegistrazione();

        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

        if(scelta==1) {
            var ricercaLibroTitolo=new RicercaLibroTitolo();

            if(ricercaLibroTitolo.isTornaIndietro()) {
                controllo=false;
            }

            else {
                libro=ricercaLibroTitolo.getLibro();
            }
        }

        if(scelta==2) {
            var ricercaLibroAutore=new RicercaLibroAutore();

            if(ricercaLibroAutore.isTornaIndietro()) {
                controllo=false;
            }

            else {
                libro=ricercaLibroAutore.getLibro();
            }
        }

        if(scelta==3) {
            var ricercaLibroAutoreAnnoPubblicazione=new RicercaLibroAutoreAnnoPubblicazione();

            if(ricercaLibroAutoreAnnoPubblicazione.isTornaIndietro()) {
                controllo=false;
            }

            else {
                libro=ricercaLibroAutoreAnnoPubblicazione.getLibro();
            }
        }

        if(controllo&&scelta!=4) {

            var visualizzazioneLibro = new VisualizzazioneLibro(menuProvenienza,libro);

            if(visualizzazioneLibro.getScelta()==1) {
                controllo=false;
            }

            if(visualizzazioneLibro.getScelta()==2) {
                scelta=4;
            }

        }

        return controllo;
    }

    private boolean cercaLibroUtenteRegistrato(boolean controllo, int menuProvenienza) {

        NuovaSchermata.nuovaSchermata();
        RicercaLibroMessaggi.menuUtenteRegistrato();

        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

        if(scelta==1) {
            var ricercaLibroTitolo=new RicercaLibroTitolo();

            if(ricercaLibroTitolo.isTornaIndietro()) {
                controllo=false;
            }

            else {
                libro=ricercaLibroTitolo.getLibro();
            }
        }

        if(scelta==2) {
            var ricercaLibroAutore=new RicercaLibroAutore();

            if(ricercaLibroAutore.isTornaIndietro()) {
                controllo=false;
            }

            else {
                libro=ricercaLibroAutore.getLibro();
            }
        }

        if(scelta==3) {
            var ricercaLibroAutoreAnnoPubblicazione=new RicercaLibroAutoreAnnoPubblicazione();

            if(ricercaLibroAutoreAnnoPubblicazione.isTornaIndietro()) {
                controllo=false;
            }

            else {
                libro=ricercaLibroAutoreAnnoPubblicazione.getLibro();
            }
        }

        if(controllo&&scelta!=4) {

            var visualizzazioneLibro = new VisualizzazioneLibro(menuProvenienza,libro);
            scelta = visualizzazioneLibro.getScelta();

            if(visualizzazioneLibro.getScelta()==1) {
                controllo=false;
            }

            if(visualizzazioneLibro.getScelta()==2) {
                scelta=4;
            }

            if(visualizzazioneLibro.getScelta()==3) {
                scelta=5;
            }

            if(visualizzazioneLibro.getScelta()==4) {
                scelta=6;
            }
        }

        return controllo;
    }

    private boolean cercaLibroUtenteRegistratoConsigli(boolean controllo, int menuProvenienza) {

        NuovaSchermata.nuovaSchermata();
        RicercaLibroMessaggi.menuUtenteRegistratoConsigli();

        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(3);

        if(scelta==1) {
            var ricercaLibroTitolo=new RicercaLibroTitolo();

            if(ricercaLibroTitolo.isTornaIndietro()) {
                controllo=false;
            }

            else {
                libro=ricercaLibroTitolo.getLibro();
            }
        }

        if(scelta==2) {
            var ricercaLibroAutore=new RicercaLibroAutore();

            if(ricercaLibroAutore.isTornaIndietro()) {
                controllo=false;
            }

            else {
                libro=ricercaLibroAutore.getLibro();
            }
        }

        if(scelta==3) {
            var ricercaLibroAutoreAnnoPubblicazione=new RicercaLibroAutoreAnnoPubblicazione();

            if(ricercaLibroAutoreAnnoPubblicazione.isTornaIndietro()) {
                controllo=false;
            }

            else {
                libro=ricercaLibroAutoreAnnoPubblicazione.getLibro();
            }
        }

        if(controllo) {

            var visualizzazioneLibro = new VisualizzazioneLibro(menuProvenienza,libro);
            scelta = visualizzazioneLibro.getScelta();
        }

        return controllo;
    }

    /**
     * Restituisce la scelta effettuata
     * del menu di selezione della modalità
     * di ricerca dei libri.
     */

    public int getScelta() {
        return scelta;
    }

    public Libro getLibro() {
        return libro;
    }
}
