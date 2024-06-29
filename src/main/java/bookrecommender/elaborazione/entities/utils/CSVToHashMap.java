package bookrecommender.elaborazione.entities.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.HashMap;

/**
 * Crea un hashmap "globale", usando il pattern Singleton, contenente i dati di un file {@code .csv} con il fine
 * di ottimizzare le performance di lettura di quest'ultimo
 *
 * @author Leonardo Basso
 */
public class CSVToHashMap {
    private static CSVToHashMap instance;
    private HashMap<String, String[]> data;

    /**
     * Costruttore privato nel caso non esista già una vecchia istanza. Inizializza una nuova hashmap che verrà
     * condivisa "globalmente"
     */
    private CSVToHashMap() {
        data = new HashMap<>();
    }

    /**
     * Se esiste un istanza dell'oggetto, allora viene ritornata quella istanza, altrimenti viene inizializzata
     * la prima istanza
     *
     * @return instance, l'istanza corrente (vecchia o nuova)
     */
    public static CSVToHashMap getInstance() {
        if (instance == null) {
            instance = new CSVToHashMap();
        }
        return instance;
    }

    /**
     * Dati un file {@code .csv}, una colonna che da la chiave e un array di colonne come argomento, genera
     * un'HashMap per tutto il file {@code .csv}
     *
     * @param keyColumn la colonna che fa da chiave per l'hashmap
     * @param headers   le colonne che fanno da argomenti
     * @param path      path   la posizione del file .csv
     */
    public void hashCsv(String keyColumn, String[] headers, String path) { // Init reader
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
                data.put(key, values); // Init HashMap per ogni riga
            }
            parser.close();
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Data una chiave di una HashMap, questa funzione ritorna i valori (l'array) conseguenti
     *
     * @param key La chiave della HashMap
     * @return l'array con i valori legati alla chiave
     */
    public String[] getValues(String key) {
        return data.get(key);
    }

    /**
     * Aggiunge un elemento all'HashMap, utile per evitare di ricreare l'HashMap a ogni modifica
     *
     * @param key    la chiave da aggiungere
     * @param values l'array di stringhe da aggiungere
     */
    public void add(String key, String[] values) {
        data.put(key, values);
    }

    /**
     * Ritorna {@code true} se esiste la chiave in input, altrimenti ritorna {@code false}. È usato per controllare
     * se va fatto un hash
     *
     * @param key la chiave da cercare
     * @return se la chiave esiste
     */
    public boolean hasKey(String key) {
        return data.containsKey(key);
    }
}
