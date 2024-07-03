package bookrecommender.elaborazione.dao.daoimpl;

import bookrecommender.elaborazione.dao.ConsigliatoDao;
import bookrecommender.elaborazione.entities.Consigliato;
import bookrecommender.elaborazione.entities.Libro;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
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

public class ConsigliatoDaoImpl implements ConsigliatoDao {


  //CAMPO

  /**
   * Costante che rappresenta l'header del file
   * Librerie.dati.csv.
   */

  private static final String[] HEADERS = {"UserId","LibroId"};

  private static final String[] HEADERS_ASSEGNAMENTO = {"ConsigliatoId","LibroId"};


  //METODI

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

  @Override
  public void add(String userId, String  libroId) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter("data/ConsigliLibri.dati.csv", true));
    writer.write("\""+userId+"\","+libroId+"\n");
    writer.close();
  }

  @Override
  public void addLibroConsigliato(String id, String  libroId) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter("data/AssegnamentoConsigliatiLibri.dati.csv", true));
    writer.write(id+","+libroId+"\n");
    writer.close();
  }

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
