package bookrecommender.elaborazione.entities.utils.csvutils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.HashMap;

/**
 * Questa classe si occupa delle operazioni di hashing di un file .csv
 *
 * @author Leonardo Basso, Angelo Penafiel
 */
public class CsvHasher {

    /**
     * Dato un file .csv, ritorna una HashMap con una key e un'array di stringhe con ogni elemento di un
     * header che corrisponde a quella stringa
     *
     * @param keyColumn la colonna che fa da hash key
     * @param headers   la lista degli header nel file .csv
     * @param path      la location del file .csv
     * @return
     */
    public static HashMap<String, String[]> hashCsv(String keyColumn, String[] headers, String path) {
        HashMap<String, String[]> data = new HashMap<>();

        try {
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
                data.put(key, values);
            }

            parser.close();
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    public static HashMap<String, String[]> hashCsv(String keyColumn1, String keyColumn2, String[] headers, String path) {
        HashMap<String, String[]> data = new HashMap<>();

        try {
            FileReader reader = new FileReader(path);
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(headers)
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser parser = new CSVParser(reader, csvFormat);

            for (CSVRecord record : parser) {
                String key = record.get(keyColumn1) + "-" + record.get(keyColumn2);
                String[] values = new String[headers.length];
                for (int i = 0; i < headers.length; i++) {
                    values[i] = record.get(headers[i]);
                }
                data.put(key, values);
            }

            parser.close();
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
