package bookrecommender.elaborazione.entities.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.util.HashMap;


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

    /**
     *
     *
     * @param keyColumn colonna della chiave dell'Hashmap
     * @param headers le intestazioni delle colonne da estrarre
     * @param path la posizione del file .csv
     * @return
     *
     *
     *
     *
     *
     */
    public static HashMap<String, String[]> hashCsv(String keyColumn, String[] headers, String path) {
        try {
            HashMap<String, String[]> output = new HashMap<>();

            FileReader reader = new FileReader(path);
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser parser = new CSVParser(reader, csvFormat);

            for (CSVRecord record : parser) {
                String key = record.get(keyColumn);
                String[] values = new String[headers.length];

                for (int i = 0; i < headers.length; i++) {
                    values[i] = record.get(headers[i]);
                }

                output.put(key, values);
            }

            parser.close();
            reader.close();
            return output;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






}
