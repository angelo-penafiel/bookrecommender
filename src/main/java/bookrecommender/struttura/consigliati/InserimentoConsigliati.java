package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.InserimentoConsigliatiMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InserimentoConsigliati  {

  public InserimentoConsigliati(String UserID, Libro l)throws IOException {


    String csvFile = "ConsigliLibri.dati.csv";
    String targetValue = UserID;
    String[] updatedRow = {UserID,"", "", ""};

    List<CSVRecord> records = new ArrayList<>();

    // Leggere il file CSV
    try (
            Reader reader = new FileReader(csvFile)) {
      Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.parse(reader);
      for (CSVRecord record : csvRecords) {
        // Se la riga contiene il valore target, sostituiscila con la nuova riga
        if (record.get(0).equals(targetValue)) { // Supponendo che il valore target sia nella prima colonna
          records.add(
                  new CSVRecord(
                  CSVFormat.DEFAULT.getRecordSeparator(),
                  updatedRow,
                  record.getComment()
          ));
        } else {
          records.add(record);
        }
      }
    } catch (
            IOException e) {
      e.printStackTrace();
    }

    // Scrivere il file CSV aggiornato
    try (
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
      for (CSVRecord record : records) {
        csvPrinter.printRecord(record);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }










   /* var ricercaLibro=new RicercaLibro(2);
    Libro libro = ricercaLibro.getLibro();
    int scelta = ricercaLibro.getScelta();
*/
    NuovaSchermata.nuovaSchermata();



    InserimentoConsigliatiMessaggi.consigliatoAggiunto();

  }

}
