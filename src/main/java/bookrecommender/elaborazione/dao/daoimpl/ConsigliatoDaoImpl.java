package bookrecommender.elaborazione.dao.daoimpl;

import bookrecommender.elaborazione.dao.ConsigliatoDao;
import bookrecommender.elaborazione.entities.Consigliato;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
 * Ha lo scopo di prelevare e aggiugere i dati
 * dei file csv (ConsigliLibri.dati.csv e
 * AssegnamentoConsigliatiLibri.dati.csv) e creare
 * oggetti o campi di classe Consigliato.
 *
 * @author Angelo Penafiel e Lorenzo Beretta
 * @version 1.0
 */

public class ConsigliatoDaoImpl implements ConsigliatoDao {


  //CAMPI

  /**
   * Costante che rappresenta l'header del file
   * ConsigliLibri.dati.csv.
   */

  private static final String[] HEADERS = {"UserId","LibroId"};

  /**
   * Costante che rappresenta l'header del file
   * AssegnamentoConsigliatiLibri.dati.csv.
   */

  private static final String[] HEADERS_ASSEGNAMENTO = {"ConsigliatoId","LibroId"};


  //METODI

  /**
   * Restituisce un oggetto Consigliato dato
   * lo userId e l'id del libro corrente.
   *
   * @param userId indica lo userId
   *
   * @param libroId indica l'id del libro
   *                corrente
   *
   * @return oggetto Consigliato
   *
   * @throws IOException nel caso in cui si
   * verifica un errore in fase di apertura
   * e scrittura dei file
   */

  @Override
  public Consigliato getByUserIdAndLibroId(String userId, String libroId) throws IOException {

    Consigliato consigliato=null;

    Reader in = new FileReader("data/ConsigliLibri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    int id=0;

    for (CSVRecord record : records) {

      String userIdC=record.get("UserId");
      String libroIdC=record.get("LibroId");

      if(userIdC.equals(userId)&&libroIdC.equals(libroId)) {
        consigliato=new Consigliato(Integer.toString(id),userId,libroId,
            getLibriConsigliatiById(Integer.toString(id)));
      }

      id++;

    }

    return consigliato;
  }

  /**
   * Restituisce una lista di id di libri
   * consigliati dato l'id dell'oggetto
   * Consigliato.
   *
   * @param id indica l'id dell'oggetto
   *           Consigliato
   *
   * @return lista di id di libri consigliati
   *
   * @throws IOException nel caso in cui si
   * verifica un errore in fase di apertura
   * e scrittura dei file
   */

  @Override
  public List<String> getLibriConsigliatiById(String id) throws IOException {

    List<String> consigliati=new ArrayList<>();

    Reader in = new FileReader("data/AssegnamentoConsigliatiLibri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS_ASSEGNAMENTO)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    for (CSVRecord record : records) {

      String consigliatoId=record.get("ConsigliatoId");
      String libroId=record.get("LibroId");

      if(consigliatoId.equals(id)) {
        consigliati.add(libroId);
      }

    }

    return consigliati;
  }

  /**
   * Aggiunge lo userId e l'id del libro
   * corrente dati al file ConsigliLibri.dati.csv.
   *
   * @param userId indica lo userId
   *
   * @param libroId indica l'id del libro corrente
   *
   * @throws IOException nel caso in cui si
   * verifica un errore in fase di apertura
   * e scrittura dei file
   */

  @Override
  public void add(String userId, String  libroId) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter("data/ConsigliLibri.dati.csv", true));
    writer.write("\""+userId+"\","+libroId+"\n");
    writer.close();
  }

  /**
   * Aggiunge l'id dell'oggetto Consigliato
   * e l'id del libro consigliato dati al file
   * AssegnamentoConsigliatiLibri.dati.csv.
   *
   * @param id indica l'id dell'oggetto Consigliato
   *
   * @param libroConsigliatoId indica l'id del
   *                           libro consigliato
   *
   * @throws IOException nel caso in cui si
   * verifica un errore in fase di apertura
   * e scrittura dei file
   */

  @Override
  public void addLibroConsigliato(String id, String libroConsigliatoId) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter("data/AssegnamentoConsigliatiLibri.dati.csv", true));
    writer.write(id+","+ libroConsigliatoId +"\n");
    writer.close();
  }

  /**
   * Restituisce un HashMap, contente come
   * chiave l'id del libro consigliato e come
   * valore il numero di volte che è stato
   * consigliato dagli utenti, dato l'id del
   * libro corrente.
   *
   * @param libroId l'id del libro corrente
   *
   * @return HashMap contente come chiave l'id
   * del libro consigliato e come valore il
   * numero di volte che è stato consigliato
   * dagli utenti
   *
   * @throws IOException nel caso in cui si
   * verifica un errore in fase di apertura
   * e scrittura dei file
   */

  @Override
  public HashMap<String, Integer> getLibriConsigliatiCountedByLibroId(String libroId) throws IOException {

    HashMap<String, Integer> consigliatiCounted=new HashMap<>();

    List<Consigliato> consigliati=getAllByLibroId(libroId);

    for(Consigliato consigliato:consigliati) {

      for(String libroConsigliato:consigliato.getLibriConsigliati()) {
        if(consigliatiCounted.get(libroConsigliato)==null) {
          consigliatiCounted.put(libroConsigliato,1);
        }

        else {
          int size=consigliatiCounted.get(libroConsigliato);
          consigliatiCounted.put(libroConsigliato,size+1);
        }
      }
    }

    return consigliatiCounted;
  }

  /**
   * Restituisce una lista di oggetti
   * Consigliato dato l'id del libro corrente.
   *
   * @param libroId indica l'id del libro
   *                corrente
   *
   * @return lista di oggetti Consigliato
   *
   * @throws IOException nel caso in cui si
   * verifica un errore in fase di apertura
   * e scrittura dei file
   */

  @Override
  public List<Consigliato> getAllByLibroId(String libroId) throws IOException {

    List<Consigliato> consigliati=new ArrayList<>();

    Reader in = new FileReader("data/ConsigliLibri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    int id=0;

    for (CSVRecord record : records) {

      String userIdC=record.get("UserId");
      String libroIdC=record.get("LibroId");

      if(libroIdC.equals(libroId)) {
        consigliati.add(new Consigliato(Integer.toString(id),userIdC,libroId,
            getLibriConsigliatiById(Integer.toString(id))));
      }

      id++;

    }

    return consigliati;
  }

}
