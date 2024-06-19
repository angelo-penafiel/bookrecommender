package bookrecommender.elaborazione.entities.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;

/**
 * Questa classe contiene le utilità per il CSVReader, create per non ripetere il codice
 *
 * @author Leonardo Basso
 */
public class CSVUtils {
    /**
     * Questo metodo, dato un input, restituisce l'input se esiste in una data intestazione, altrimenti restituisce null
     * @param toFind La stringa da trovare
     * @param header Intestazione in cui deve essere eseguita la ricerca della stringa
     * @param path la posizione del file .csv
     * @return toFind se corrisponde nel file, altrimenti restituisce null
     */
    public static String find(String toFind, String header, String path) {
        try {
            FileReader reader = new FileReader(path);
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser parser = new CSVParser(reader, csvFormat);
            for (CSVRecord record : parser) {
                if (record.get(header).equals(toFind)) return record.get(header);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
