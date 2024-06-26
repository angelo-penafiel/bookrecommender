package bookrecommender.elaborazione.dao.daoimpl;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.entities.Libreria;
import bookrecommender.elaborazione.entities.Libro;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Classe che implementa il design pattern DAO.
 * La sua funzione è quella di separare la logica
 * di accesso ai dati dalla logica di business.
 * Ha lo scopo di prelevare i dati dai file csv e
 * creare oggetti o campi di classe Libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class LibreriaDaoImpl implements LibreriaDao {


  //CAMPO

  /**
   * Costante che rappresenta l'header del file
   * Librerie.dati.csv.
   */

  private static final String[] HEADERS_LIBRERIA = {"Nome","UserId"};

  /**
   * Costante che rappresenta l'header del file
   * AssegnamentoLibrerieLibri.dati.csv.
   */

  private static final String[] HEADERS_ASSEGNAMENTO = {"LibreriaId","LibroId"};


  //METODI

  /**
   * Restituisce un valore boolean che indica la
   * presenza di una libreria con il nome dato.
   *
   * @param nome indica il nome
   *
   * @return se esiste la libreria
   */

  @Override
  public boolean existsByNome(String nome, String userId) throws IOException {

    boolean exists=false;

    Reader in = new FileReader("data/Librerie.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS_LIBRERIA)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    for (CSVRecord record : records) {

      String nomeLibreria=record.get("Nome").toLowerCase();
      String userIdlibreria=record.get("UserId").toLowerCase();

      if(nomeLibreria.equals(nome)&&userIdlibreria.equals(userId)) {
        exists=true;
      }

    }

    return exists;
  }

  /**
   * Aggiunge una libreria dati un nome e lo
   * userId.
   *
   * @param nome indica il nome
   *
   * @param userId indica lo userId
   *               
   * @return la libreria aggiunta
   */

  @Override
  public Libreria add(String nome, String userId) throws IOException {

    Libreria libreria = null;

    BufferedWriter writer = new BufferedWriter(new FileWriter("data/Librerie.dati.csv", true));
    writer.write("\""+nome+"\",\""+userId+"\""+ "\n");
    writer.close();

    Reader in = new FileReader("data/Librerie.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS_LIBRERIA)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    int i=0;

    for (CSVRecord record : records) {

      String nomeLibreria=record.get("Nome").toLowerCase();
      String userIdlibreria=record.get("UserId").toLowerCase();

      if(nomeLibreria.equals(nome)&&userIdlibreria.equals(userId)) {
        libreria=new Libreria(i,nome,userId);
      }

      i++;

    }

    return libreria;
  }

  /**
   * Restituisce una lista di id delle librerie
   * che hanno come userId quello dato.
   *
   * @param userId indica lo userId
   *               
   * @return la lista di id
   */

  @Override
  public List<Integer> getIdsByUserId(String userId) throws IOException {

    List<Integer> ids= new ArrayList<>();

    Reader in = new FileReader("data/Librerie.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS_LIBRERIA)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    int i=0;

    for (CSVRecord record : records) {

      String userIdlibreria=record.get("UserId").toLowerCase();

      if(userIdlibreria.equals(userId)) {
        ids.add(i);
      }

      i++;

    }

    return ids;
  }

  /**
   * Restituisce una lista di nomi delle
   * librerie che hanno come id quelli dati.
   *
   * @param ids indica gli id delle librerie
   *            
   * @return la lista di nomi
   */

  @Override
  public List<String> getNomiByIds(List<Integer> ids) throws IOException {

    List<String> nomi= new ArrayList<>();

    Reader in = new FileReader("data/Librerie.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS_LIBRERIA)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    int i=0;

    for (CSVRecord record : records) {

      String nomeLibreria=record.get("Nome").toLowerCase();

      for(Integer id:ids) {
        if(i==id) {
          nomi.add(nomeLibreria);
        }
      }

      i++;

    }

    return nomi;
  }

  /**
   * Restituisce un oggetto libreria correlato
   * all'id dato.
   *
   * @param id indica l'id 
   *
   * @return la libreria
   */

  @Override
  public Libreria getById(Integer id) throws IOException {

    Libreria libreria = null;

    Reader in = new FileReader("data/Librerie.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS_LIBRERIA)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    int i=0;

    for (CSVRecord record : records) {

      String nomeLibreria=record.get("Nome").toLowerCase();
      String userIdlibreria=record.get("UserId").toLowerCase();
      
      if(i==id) {
        libreria=new Libreria(id,nomeLibreria,userIdlibreria);
      }
      
      i++;

    }
    
    return libreria;
  }

  /**
   * Restituisce una lista di id dei libri correlati
   * all'id della libreria dato.
   *
   * @param id indica l'id della libreria
   *
   * @return lista dei libri
   */

  @Override
  public List<Integer> getIdsLibriByIdLibreria(Integer id) throws IOException {

    List<Integer> idsLibri=new ArrayList<>();

    Reader in = new FileReader("data/AssegnamentoLibrerieLibri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS_ASSEGNAMENTO)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    for (CSVRecord record : records) {

      Integer idLibreria= Integer.valueOf(record.get("LibreriaId"));
      Integer idLibro= Integer.valueOf(record.get("LibroId"));

      if(idLibreria==id) {
        idsLibri.add(idLibro);
      }

    }

    return idsLibri;
  }

  /**
   * Restituisce un valore boolean che indica se un
   * libro è già presnte nella libreria.
   *
   * @param libreria indica la libreria
   *
   * @param libro indica il libro
   *
   * @return se un libro è presente
   */

  @Override
  public boolean existsLibro(Libreria libreria, Libro libro) throws IOException {

    boolean exists=false;

    Reader in = new FileReader("data/AssegnamentoLibrerieLibri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS_ASSEGNAMENTO)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    for (CSVRecord record : records) {

      Integer idLibreria= Integer.valueOf(record.get("LibreriaId"));
      Integer idLibro= Integer.valueOf(record.get("LibroId"));

      if(idLibreria==libreria.getId()&&idLibro==libro.getId()) {
        exists=true;
      }

    }

    return exists;
  }

  /**
   * Aggiunge un libro nella libreria data.
   *
   * @param libreria indica la libreria
   *
   * @param libro indica il libro
   */

  @Override
  public void addLibro(Libreria libreria, Libro libro) throws IOException {

    BufferedWriter writer = new BufferedWriter(new FileWriter("data/AssegnamentoLibrerieLibri.dati.csv", true));
    writer.write(libreria.getId()+","+ libro.getId()+"\n");
    writer.close();

  }
}
