
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.struttura.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroTitoloMessaggi;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class RicercaLibroTitolo {

    private Libro libro;

    private String titolo;

    public RicercaLibroTitolo() {

        NuovaSchermata.nuovaSchermata();
        RicercaLibroTitoloMessaggi.intestazioneInserimentoTitolo();

        titolo=RicercaLibroTitoloMessaggi.inserimentoTitolo();

        List<Integer> libriTrovati;
        try {
            libriTrovati=RicercaLibroTitolo.cercaLibroTitolo(titolo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Integer libroSelezionato=ricercaSelezioneLibroTitolo(libriTrovati);

        try {
            libro=RicercaLibro.caricamentoLibro(libroSelezionato);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(libro.getTitolo());
        System.out.println(libro.getAutori());
        System.out.println(libro.getAnnoPubblicazione());
        System.out.println(libro.getEditore());
        System.out.println(libro.getCategorie());
    }

    public static Integer ricercaSelezioneLibroTitolo(List<Integer> libriTrovati) {

        List<List<Object>> opzioniTitolo;

        Integer libroSelezionato;

        try {
            opzioniTitolo=caricamentoOpzioniTitolo(libriTrovati);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NuovaSchermata.nuovaSchermata();

        if(opzioniTitolo.size()>RicercaLibro.MAX_RISULTATI_PAGINA) {

            int paginaCorrente=0;
            boolean controllo;

            do {
                controllo=true;

                RicercaLibroTitoloMessaggi.stampaOpzioniTitoloPagina(opzioniTitolo,paginaCorrente);
                libroSelezionato=SceltaMenuMessaggi.inserimentoSceltaOpzioniPagina(paginaCorrente,
                    opzioniTitolo.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1,opzioniTitolo.size());

                if(libroSelezionato==-1||libroSelezionato==-2) {
                    controllo=false;
                }

                if(libroSelezionato==-1) {
                    paginaCorrente=paginaCorrente+1;
                }

                if(libroSelezionato==-2) {
                    paginaCorrente=paginaCorrente-1;
                }

                NuovaSchermata.nuovaSchermata();

            } while (!controllo);
        }

        else {
            RicercaLibroTitoloMessaggi.stampaOpzioniTitolo(opzioniTitolo);
            libroSelezionato= SceltaMenuMessaggi.inserimentoSceltaMenu(opzioniTitolo.size());
            NuovaSchermata.nuovaSchermata();
        }

        libroSelezionato=libriTrovati.get(libroSelezionato-1);

        return libroSelezionato;
    }

    public static List<List<Object>> caricamentoOpzioniTitolo(List<Integer> libriTrovati) throws IOException {

        List<List<Object>> opzioniTitolo = new ArrayList<>();

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        int i=0;

        for (CSVRecord record : records) {

            String titolo=record.get("Title").toLowerCase();
            Integer annoPubblicazione=Integer.parseInt(record.get("Publish Date (Year)"));;

            for(Integer libroTrovato:libriTrovati) {
                if(libroTrovato==i) {

                    List<Object> oggetti=new ArrayList<>();
                    oggetti.add(titolo);
                    oggetti.add(annoPubblicazione);

                    opzioniTitolo.add(oggetti);
                }
            }

            i++;

        }

        return opzioniTitolo;
    }

    public static List<Integer> cercaLibroTitolo(String titoloInserito) throws IOException {

        List<Integer> libriTrovati=new ArrayList<>();

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        int i=0;

        for (CSVRecord record : records) {

            String titolo=record.get("Title").toLowerCase();

            if(titolo.contains(titoloInserito)) {
                libriTrovati.add(i);
            }

            i++;
        }

        return libriTrovati;
    }

}
