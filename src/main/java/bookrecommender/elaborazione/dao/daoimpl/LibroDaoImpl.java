package bookrecommender.elaborazione.dao.daoimpl;

import bookrecommender.elaborazione.dao.LibroDao;
import bookrecommender.elaborazione.entities.Libro;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Classe che implementa il design pattern DAO.
 * La sua funzione è quella di separare la logica
 * di accesso ai dati dalla logica di business.
 * Ha lo scopo di prelevare i dati del file csv
 * (Libri.dati.csv) e creare oggetti o campi di classe
 * Libro.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class LibroDaoImpl implements LibroDao {

  //CAMPO

  /**
   * Campo che rappresenta l'header del file
   * Libri.dati.csv.
   */

  private static final String[] HEADERS = {"Title","Authors","Description","Category",
      "Publisher","Price Starting With ($)","Publish Date (Month)","Publish Date (Year)"};

  //METODI

  /**
   * Restituisce una lista di id dei libri correlati
   * dato il titolo del libro cercato.
   *
   * @param titolo indica il titolo
   *
   * @return lista di id correlati al titolo
   */

  @Override
  public List<Integer> getIdsByTitolo(String titolo) throws IOException {

    List<Integer> libriTrovati=new ArrayList<>();

    Reader in = new FileReader("data/Libri.dati.csv");


    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    int i=0;

    for (CSVRecord record : records) {

      String titoloLibro=record.get("Title").toLowerCase();

      if(titoloLibro.contains(titolo)) {
        libriTrovati.add(i);
      }

      i++;
    }

    return libriTrovati;
  }

  /**
   * Restituisce una lista di oggetti di tipo
   * libro, di cui sono forniti solamente i
   * campi titolo e anno di pubblicazione,
   * dato una lista di id di libri.
   *
   * @param libriId indica la lista di libri
   *
   * @return lista di titoli e anno di pubblicazione
   *         cercati
   */

  @Override
  public List<Libro> getTitoloAndAnnoByIds(List<Integer> libriId) throws IOException {

    List<Libro> opzioniTitoloAnno = new ArrayList<>();

    Reader in = new FileReader("data/Libri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    int i=0;

    for (CSVRecord record : records) {

      String titolo=record.get("Title").toLowerCase();
      Integer annoPubblicazione=Integer.parseInt(record.get("Publish Date (Year)"));;

      for(Integer libroTrovato:libriId) {
        if(libroTrovato==i) {
          opzioniTitoloAnno.add(new Libro(titolo,annoPubblicazione));
        }
      }

      i++;

    }

    return opzioniTitoloAnno;
  }

  @Override
  public String getTitoloById(Integer id) throws IOException {

    String titolo="";

    Reader in = new FileReader("data/Libri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    int i=0;

    for (CSVRecord record : records) {

      String titoloL=record.get("Title").toLowerCase();

      if(id==i) {
        titolo=titoloL;
      }

      i++;

    }

    return titolo;
  }

  /**
   * Restituisce una lista di autori correlati
   * al nome dell'autore fornito.
   *
   * @param autore indica l'autore di cui bisogna
   *               effettuare la ricerca
   *
   * @return lista di autori cercati
   */

  @Override
  public List<String> getAutoriByAutore(String autore) throws IOException {

    HashMap<String, Integer> autori=getAllAutori();

    List<String> autoriTrovati = new ArrayList<>();

    for (String autore2 : autori.keySet()) {
      if (autore2.contains(autore)) {
        autoriTrovati.add(autore2);
      }
    }

    return autoriTrovati;
  }

  /**
   * Restituisce la lista di autori presenti
   * all'interno di tutto il file Libri.dati.csv.
   * Nella lista non sono presenti duplicati.
   *
   * @return hashmap degli autori globali
   */

  @Override
  public HashMap<String, Integer> getAllAutori() throws IOException {

    HashMap<String, Integer> autori = new HashMap<>();

    Reader in = new FileReader("data/Libri.dati.csv");

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

  /**
   * Restituisce una lista di id di libri
   * correlati al nome dell'autore fornito.
   *
   * @param autore indica l'autore di cui bisogna
   *               effettuare la ricerca
   *
   * @return lista di id di libri cercati
   */

  @Override
  public List<Integer> getIdsByAutore(String autore) throws IOException {

    //se seleziona angela shelf i risultati saranno solo angela shelf e non rientrerà angela shelf and walker

    List<Integer> libri=new ArrayList<>();

    Reader in = new FileReader("data/Libri.dati.csv");

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
          if(authors.substring(j,i).equals(autore)){
            libri.add(cont);
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
          libri.add(cont);
        }
      }

      cont++;
    }

    return libri;
  }

  /**
   * Restituisce una lista di oggetti di tipo
   * libro, di cui sono forniti solamente i
   * campi dell'autore e del titolo, correlati
   * agli autori e all'anno di pubblicazioni
   * forniti.
   *
   * @param autori indica gli autori di cui
   *               bisogna effettuare la ricerca
   *
   * @param annoPubblicazione indica l'anno di
   *                          pubblicazione di
   *                          cui bisogna ef-
   *                          -fettuare la ri-
   *                          -cerca
   *
   * @return lista di autori e titoli cercati
   */

  @Override
  public List<Libro> getAutoreAndTitoloByAutoriAndAnno(List<String> autori,
      Integer annoPubblicazione) throws IOException {

    List<Libro> libri=new ArrayList<>();

    Reader in = new FileReader("data/Libri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    for(String autore:autori) {
      for (CSVRecord record : records) {

        String authors=record.get("Authors").toLowerCase();
        Integer annoPubblicazioneLibro=Integer.parseInt(record.get("Publish Date (Year)"));

        authors=authors.trim();

        if(annoPubblicazioneLibro.equals(annoPubblicazione)) {
          int j=3;

          boolean controllo=false;

          for(int i=0;i<authors.length();i++) {
            if(',' == authors.charAt(i)&&i-j>2) {
              if(authors.substring(j,i).equals(autore)){
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
              controllo=true;
            }
          }

          if(controllo) {
            libri.add(new Libro(record.get("Title").toLowerCase(),autore));
          }
        }
      }
    }

    return libri;
  }

  /**
   * Restituisce l'id del libro correlato al
   * titolo fornito.
   *
   * @param titolo indica il titolo di cui
   *               bisogna effettuare la
   *               ricerca
   *
   * @return id del libro cercato
   */

  @Override
  public Integer getIdByTitolo(String titolo) throws IOException {

    Integer libriSelezionato = 0;

    Reader in = new FileReader("data/Libri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    int i=0;

    for (CSVRecord record : records) {

      String titoloLibro=record.get("Title").toLowerCase();

      if(titoloLibro.contains(titolo)) {
        libriSelezionato=i;
      }

      i++;
    }

    return libriSelezionato;
  }

  /**
   * Restituisce l'oggetti di tipo libro
   * correlato all'id.
   *
   * @param id indica l'id del libro di cui
   *           bisogna effettuare la ricerca
   *
   * @return libro correlato all'id
   */

  @Override
  public Libro getById(Integer id) throws IOException {

    Libro libro;

    Reader in = new FileReader("data/Libri.dati.csv");

    var csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    CSVRecord csvRecord = csvFormat.parse(in).getRecords().get(id);

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

    libro=new Libro(id,titolo,autori,annoPubblicazione,editore,categorie);

    return libro;
  }

}
