
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.struttura.ricercalibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroMessaggi;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RicercaLibro {

    public static Integer MAX_RISULTATI_PAGINA=7;

    private int scelta;

    private List<String> autori;

    public RicercaLibro(int continuaSenzaRegistrazione) {

        var controllo = true;

        do {

            NuovaSchermata.nuovaSchermata();
            RicercaLibroMessaggi.menuSenzaRegistrazione();

            scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(6);

            int modalitaRicerca=scelta;

            if(scelta==1) {
                var ricercaLibroTitolo=new RicercaLibroTitolo();
            }

            if(scelta==2) {
                var ricercaLibroAutore=new RicercaLibroAutore();
            }

            if(scelta==3) {
                var ricercaLibroAutoreAnnoPubblicazione=new RicercaLibroAutoreAnnoPubblicazione();
            }

            if(scelta==4) {

            }

            if(scelta==5) {

            }

        } while(!controllo);
    }

    private void caricamentoLibri() throws IOException {

        this.autori=new ArrayList<>();

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

            List<String> autori=new ArrayList<>();
            List<String> categorie=new ArrayList<>();

            authors=authors.trim();
            categories=categories.trim();

            int j=3;

            for(int i=0;i<authors.length();i++) {
                if(',' == authors.charAt(i)&&i-j>2) {
                    autori.add(authors.substring(j,i));

                    //caricamento lista globale autori
                    var controllo=true;

                    for(String autore:this.autori) {
                        if(autore.equalsIgnoreCase(authors.substring(j,i))) {
                            controllo=false;
                        }
                    }

                    if(controllo) {
                        this.autori.add(authors.substring(j,i));
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
                autori.add(authors.substring(j));
            }

            j=0;

            for(int i=0;i<categories.length();i++) {
                if(',' == categories.charAt(i)&&i-j>2) {
                    categorie.add(categories.substring(j,i-1));
                    j=i+2;
                }

                if(',' == categories.charAt(i)&&i-j<=2) {
                    j=i+2;
                }
            }

            if(categories.length()-j>3) {
                categorie.add(categories.substring(j));
            }

            //libri.add(new Libro(titolo, autori, annoPubblicazione, editore, categorie));

        }
    }

    public static Libro caricamentoLibro(Integer libroSelezionato) throws IOException {

        Libro libro;

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
                "Publish Date (Month)","Publish Date (Year)"};

        var csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        CSVRecord csvRecord = csvFormat.parse(in).getRecords().get(libroSelezionato);

        String titolo=csvRecord.get("Title").toLowerCase();
        String authors=csvRecord.get("Authors").toLowerCase();
        Integer annoPubblicazione=Integer.parseInt(csvRecord.get("Publish Date (Year)"));
        String editore=csvRecord.get("Publisher").toLowerCase();
        String categories=csvRecord.get("Category").toLowerCase();

        List<String> autori=new ArrayList<>();
        List<String> categorie=new ArrayList<>();

        authors=authors.trim();
        categories=categories.trim();

        int j=3;

        for(int i=0;i<authors.length();i++) {
            if(',' == authors.charAt(i)&&i-j>2) {
                autori.add(authors.substring(j,i));
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
            autori.add(authors.substring(j));
        }

        j=0;

        for(int i=0;i<categories.length();i++) {
            if(',' == categories.charAt(i)&&i-j>2) {
                categorie.add(categories.substring(j,i-1));
                j=i+2;
            }

            if(',' == categories.charAt(i)&&i-j<=2) {
                j=i+2;
            }
        }

        if(categories.length()-j>3) {
            categorie.add(categories.substring(j));
        }

        libro=new Libro(titolo,autori,annoPubblicazione,editore,categorie);

        return libro;
    }

    public int getScelta() {
        return scelta;
    }

}
