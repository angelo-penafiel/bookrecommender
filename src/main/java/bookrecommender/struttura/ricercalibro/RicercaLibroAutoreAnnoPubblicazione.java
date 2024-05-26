
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.struttura.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroAutoreAnnoMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroAutoreMessaggi;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class RicercaLibroAutoreAnnoPubblicazione {

    private Libro libro;

    private String autore;

    private Integer annoPubblicazione;

    private List<String> autoriTrovati;

    private List<String> autoriTrovatiNuovo;

    private List<String> titoli;

    private String titoloSelezionato;
    
    private Integer libroSelezionato;

    public RicercaLibroAutoreAnnoPubblicazione() {

        //ci sono più angela 2006

        HashMap<String, Integer> autori;

        try {
            autori=RicercaLibroAutore.caricamentoAutori();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NuovaSchermata.nuovaSchermata();
        RicercaLibroAutoreAnnoMessaggi.intestazioneInserimentoAutore();

        autore= RicercaLibroAutoreMessaggi.inserimentoAutore();

        annoPubblicazione=RicercaLibroAutoreAnnoMessaggi.inserimentoAnnoPubblicazione();

        autoriTrovati=new ArrayList<>();
        try {
            autoriTrovati=RicercaLibroAutore.cercaAutore(autori,autore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(autoriTrovati.size()>0) {

            autoriTrovatiNuovo=new ArrayList<>();
            titoli=new ArrayList<>();
            try {
                cercaAnnoPubblicazione();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Integer titoloSelezionatoInt;
            NuovaSchermata.nuovaSchermata();

            if(autoriTrovatiNuovo.size()>RicercaLibro.MAX_RISULTATI_PAGINA) {

                int paginaCorrente=0;
                boolean controllo;

                do {
                    controllo=true;

                    RicercaLibroAutoreAnnoMessaggi.stampaOpzioniAutoreAnnoPagina(
                        autoriTrovatiNuovo,annoPubblicazione,titoli,paginaCorrente);
                    titoloSelezionatoInt=SceltaMenuMessaggi.inserimentoSceltaOpzioniPagina(paginaCorrente,
                        autoriTrovatiNuovo.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1,
                        autoriTrovatiNuovo.size());

                    if(titoloSelezionatoInt==-1||titoloSelezionatoInt==-2) {
                        controllo=false;
                    }

                    if(titoloSelezionatoInt==-1) {
                        paginaCorrente=paginaCorrente+1;
                    }

                    if(titoloSelezionatoInt==-2) {
                        paginaCorrente=paginaCorrente-1;
                    }

                    NuovaSchermata.nuovaSchermata();

                } while (!controllo);
            }

            else {
                RicercaLibroAutoreAnnoMessaggi.stampaOpzioniAutoreAnno(autoriTrovatiNuovo,annoPubblicazione,
                    titoli);
                titoloSelezionatoInt=SceltaMenuMessaggi.inserimentoSceltaMenu(autoriTrovatiNuovo.size());
                NuovaSchermata.nuovaSchermata();
            }

            titoloSelezionato=titoli.get(titoloSelezionatoInt-1);

          try {
            libroSelezionato=cercaLibro(titoloSelezionato);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }

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
    }

    private void cercaAnnoPubblicazione() throws IOException {

        Reader in = new FileReader("data/Libri.dati.csv");

        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        for(String autore:autoriTrovati) {
            for (CSVRecord record : records) {

                String authors=record.get("Authors").toLowerCase();
                Integer annoPubblicazione=Integer.parseInt(record.get("Publish Date (Year)"));

                authors=authors.trim();

                if(annoPubblicazione.equals(this.annoPubblicazione)) {
                    int j=3;

                    boolean controllo=false;

                    for(int i=0;i<authors.length();i++) {
                        if(',' == authors.charAt(i)&&i-j>2) {
                            if(authors.substring(j,i).equals(autore)){
                                autoriTrovatiNuovo.add(autore);
                                controllo=true;
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
                    }

                    if(authors.length()-j>1) {
                        if(authors.substring(j).equals(autore)){
                            autoriTrovatiNuovo.add(autore);
                            controllo=true;
                        }
                    }

                    if(controllo) {
                        titoli.add(record.get("Title").toLowerCase());
                    }
                }
            }
        }
    }

    public static List<Integer> cercaLibroAutoreAnnoPubblicazione(String autoreSelezionato,
        Integer annoSelezionato) throws IOException {

        List<Integer> libriTrovati = new ArrayList<>();
        
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

            String authors=record.get("Authors").toLowerCase();
            Integer annoPubblicazione=Integer.parseInt(record.get("Publish Date (Year)"));

            if(authors.contains(autoreSelezionato)&&annoPubblicazione.equals(annoSelezionato)) {
                libriTrovati.add(i);
            }

            i++;
        }
        
        return libriTrovati;
    }

    private Integer cercaLibro(String titoloSelezionato) throws IOException {
       
        Integer libriSelezionato = 0;
       
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

            if(titolo.contains(titoloSelezionato)) {
                libriSelezionato=i;
            }

            i++;
        }

        return libriSelezionato;
    }
}
