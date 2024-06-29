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
     *
     * @param toFind    La stringa da trovare
     * @param keyColumn keyColumn La colonna da usare come chiave
     * @param headers   Le intestazioni delle colonne da estrarre
     * @param path      la posizione del file .csv
     * @return toFind se corrisponde nel file, altrimenti restituisce null
     */
    public static String find(String toFind, String keyColumn, String[] headers, String path) {
        try {
            HashMap<String, String[]> data = hashCsv(keyColumn, headers, path);
            
            if (data.containsKey(toFind)) {
                return toFind;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Dati un file .csv, una colonna che da la chiave e un-array di colonne come argomento, genera un'HashMap per tutto il file csv
     *
     * @param keyColumn la colonna che fa da chiave per l'hashmap
     * @param headers   le colonne che fanno da argomenti
     * @param path      path   la posizione del file .csv
     * @return HashMap
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
