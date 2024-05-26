
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.struttura.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroAutoreMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroTitoloMessaggi;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class RicercaLibroAutore {

    private Libro libro;

    private String autore;

    private List<String> autoriTrovati;

    private Integer libroSelezionato;

    private String autoreSelezionato;

    public RicercaLibroAutore() {

        HashMap<String, Integer> autori;

        try {
            autori=caricamentoAutori();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NuovaSchermata.nuovaSchermata();
        RicercaLibroAutoreMessaggi.intestazioneInserimentoAutore();

        autore=RicercaLibroAutoreMessaggi.inserimentoAutore();

        autoriTrovati=new ArrayList<>();
        try {
            autoriTrovati=cercaAutore(autori,autore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Integer autoreSelezionatoInt;
        NuovaSchermata.nuovaSchermata();

        if(autoriTrovati.size()>RicercaLibro.MAX_RISULTATI_PAGINA) {

            int paginaCorrente=0;
            boolean controllo;

            do {
                controllo=true;

                RicercaLibroAutoreMessaggi.stampaOpzioniAutorePagina(autoriTrovati,paginaCorrente);
                autoreSelezionatoInt=SceltaMenuMessaggi.inserimentoSceltaOpzioniPagina(paginaCorrente,
                    autoriTrovati.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1,autoriTrovati.size());

                if(autoreSelezionatoInt==-1||autoreSelezionatoInt==-2) {
                    controllo=false;
                }

                if(autoreSelezionatoInt==-1) {
                    paginaCorrente=paginaCorrente+1;
                }

                if(autoreSelezionatoInt==-2) {
                    paginaCorrente=paginaCorrente-1;
                }
                NuovaSchermata.nuovaSchermata();

            } while (!controllo);
        }

        else {
            RicercaLibroAutoreMessaggi.stampaOpzioniAutore(autoriTrovati);
            autoreSelezionatoInt= SceltaMenuMessaggi.inserimentoSceltaMenu(autoriTrovati.size());
            NuovaSchermata.nuovaSchermata();
        }

        autoreSelezionato=autoriTrovati.get(autoreSelezionatoInt-1);

        List<Integer> libriTrovati;

        try {
            libriTrovati = RicercaLibroAutore.cercaLibroAutore(autoreSelezionato);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        libroSelezionato=ricercaSelezioneLibroTitolo(libriTrovati);

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

    public static List<Integer> cercaLibroAutore(String autoreSelezionato) throws IOException {

        //se seleziona angela shelf i risultati saranno solo angela shelf e non rientrerà angela shelf and walker

        List<Integer> libriTrovati=new ArrayList<>();

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        int cont=0;

        for (CSVRecord record : records) {

            String authors=record.get("Authors").toLowerCase();
            authors=authors.trim();

            int j=3;

            for(int i=0;i<authors.length();i++) {
                if(',' == authors.charAt(i)&&i-j>2) {
                    if(authors.substring(j,i).equals(autoreSelezionato)){
                        libriTrovati.add(cont);
                    }
                    j=i+2;
                }

                if(',' == authors.charAt(i)&&i-j<=2) {
                    j=i+2;
                }

                //per rimuovere l'and di: autore1, and autore2
                if(j<i-1&& "and ".equals(authors.substring(j, i))) {
                    j=i;
                }

                //per rimuovere and interni: autore1, autore2 and autore3, autore4
//                if(i>6&&i>j+6&&" and ".equals(authors.substring(i-6,i-1))) {
//                    autori.add(authors.substring(j,i-6));
//                    j=i-1;
//                }

            }

            if(authors.length()-j>1) {
                if(authors.substring(j).equals(autoreSelezionato)){
                    libriTrovati.add(cont);
                }
            }

            cont++;
        }

        return libriTrovati;
    }

    public static HashMap<String, Integer> caricamentoAutori() throws IOException {

        HashMap<String, Integer> autori = new HashMap<>();

        Reader in = new FileReader("data/Libri.dati.csv");

        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        for (CSVRecord record : records) {

            String authors=record.get("Authors").toLowerCase();
            authors=authors.trim();

            int j=3;

            for(int i=0;i<authors.length();i++) {
                if(',' == authors.charAt(i)&&i-j>2) {

                    //caricamento lista globale autori
                    boolean controllo=true;

                    if(autori.containsKey(authors.substring(j,i))){
                        controllo=false;
                    }

                    if(controllo) {
                        autori.put(authors.substring(j,i),0);
                    }

                    j=i+2;
                }

                if(',' == authors.charAt(i)&&i-j<=2) {
                    j=i+2;
                }

                //per rimuovere l'and di: autore1, and autore2
                if(j<i-1&& "and ".equals(authors.substring(j, i))) {
                    j=i;
                }

                //per rimuovere and interni: autore1, autore2 and autore3, autore4
//                if(i>6&&i>j+6&&" and ".equals(authors.substring(i-6,i-1))) {
//                    autori.add(authors.substring(j,i-6));
//                    j=i-1;
//                }

            }

            if(authors.length()-j>1) {

                //caricamento lista globale autori
                boolean controllo=true;

                if(autori.containsKey(authors.substring(j))){
                    controllo=false;
                }

                if(controllo) {
                    autori.put(authors.substring(j),0);
                }
            }
        }

        return autori;
    }

    public static List<String> cercaAutore(HashMap<String, Integer> autori, String autore) throws IOException {

        List<String> autoriTrovati = new ArrayList<>();

        for (String autore2 : autori.keySet()) {
            if (autore2.contains(autore)) {
                autoriTrovati.add(autore2);
            }
        }

        return autoriTrovati;
    }

    public static Integer ricercaSelezioneLibroTitolo(List<Integer> libriTrovati) {

        List<List<Object>> opzioniTitolo;

        Integer libroSelezionato;

        try {
            opzioniTitolo = RicercaLibroTitolo.caricamentoOpzioniTitolo(libriTrovati);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NuovaSchermata.nuovaSchermata();

        if (opzioniTitolo.size() > RicercaLibro.MAX_RISULTATI_PAGINA) {

            int paginaCorrente = 0;
            boolean controllo;

            do {
                controllo = true;

                RicercaLibroTitoloMessaggi.stampaOpzioniTitoloPagina(opzioniTitolo, paginaCorrente);
                libroSelezionato = SceltaMenuMessaggi.inserimentoSceltaOpzioniPagina(paginaCorrente,
                    opzioniTitolo.size() / RicercaLibro.MAX_RISULTATI_PAGINA + 1, opzioniTitolo.size());

                if (libroSelezionato == -1 || libroSelezionato == -2) {
                    controllo = false;
                }

                if (libroSelezionato == -1) {
                    paginaCorrente = paginaCorrente + 1;
                }

                if (libroSelezionato == -2) {
                    paginaCorrente = paginaCorrente - 1;
                }

                NuovaSchermata.nuovaSchermata();

            } while (!controllo);
        } else {
            RicercaLibroTitoloMessaggi.stampaOpzioniTitolo(opzioniTitolo);
            libroSelezionato = SceltaMenuMessaggi.inserimentoSceltaMenu(opzioniTitolo.size());
            NuovaSchermata.nuovaSchermata();
        }

        libroSelezionato = libriTrovati.get(libroSelezionato - 1);

        return libroSelezionato;
    }
}
