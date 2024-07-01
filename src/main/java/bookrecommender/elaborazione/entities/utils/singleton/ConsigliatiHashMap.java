package bookrecommender.elaborazione.entities.utils.singleton;

import bookrecommender.elaborazione.entities.utils.csvutils.CsvHasher;

import java.util.HashMap;

/**
 * Crea un hashmap dei libri consigliati usando il pattern Singleton.
 * L'obbiettivo è migliorare le performance in fase di ricerca
 *
 * @author Leonardo Basso
 * @see bookrecommender.struttura.consigliati.MenuConsigliati
 */
public class ConsigliatiHashMap {
    private static ConsigliatiHashMap instance;
    private HashMap<String, String[]> data;

    /**
     * Costruttore privato nel caso non esista già una vecchia istanza. Inizializza una nuova hashmap che verrà
     * condivisa "globalmente"
     */
    private ConsigliatiHashMap() {
        data = new HashMap<>();
    }

    /**
     * Se esiste un istanza dell'oggetto, allora viene ritornata quella istanza, altrimenti viene inizializzata
     * la prima istanza
     *
     * @return instance, l'istanza corrente (vecchia o nuova)
     */
    public static ConsigliatiHashMap getInstance() {
        if (instance == null) {
            instance = new ConsigliatiHashMap();
            instance.init();
        }
        return instance;
    }

    public static ConsigliatiHashMap getInstance2() {
            instance = new ConsigliatiHashMap();
            instance.init();

        return instance;
    }

    /**
     * Questo metodo privato serve a inizializzare la hashmap
     */
    private void init() {
        String[] headers = {"UserID", "id libro consigliato 1", "id libro consigliato 2", "id libro consigliato 3"};
        String path = "data/ConsigliLibri.dati.csv";

        data = CsvHasher.hashCsv("UserID", headers, path);
    }

    /**
     * Dato un UserID, questa funzione ritorna gli id dei libri che questo utente ha consigliato
     *
     * @param UserID La chiave della HashMap
     * @return l'array con gli id dei libri legati alla chiave
     */
    public String[] getValues(String UserID) {
        return data.get(UserID);
    }

    /**
     * Ritorna l'UserID associato a un UserID.
     *
     * @param userID
     * @return L'UserID associato al UserID.
     */
    public String getUserID(String userID) {
        String[] userData = data.get(userID);
        if (userData != null) {
            return userData[0];
        }
        return null;
    }

    /**
     * Ritorna il primo libro consigliato associato a un UserID.
     *
     * @param userID
     * @return Primo libro consigliato associato al UserID.
     */
    public String getFirstBook(String userID) {
        String[] userData = data.get(userID);
        if (userData != null) {
            return userData[1];
        }
        return null;
    }

    /**
     * Ritorna il secondo libro consigliato associato a un UserID.
     *
     * @param userID
     * @return Secondo libro consigliato associato al UserID.
     */
    public String getSecondBook(String userID) {
        String[] userData = data.get(userID);
        if (userData != null) {
            return userData[2];
        }
        return null;
    }

    /**
     * Ritorna il terzo libro consigliato associato a un UserID.
     *
     * @param userID
     * @return Terzo libro consigliato associato al UserID.
     */
    public String getThirdBook(String userID) {
        String[] userData = data.get(userID);
        if (userData != null) {
            return userData[3];
        }
        return null;
    }
    /**
     * Aggiunge un libro consigliato all'HashMap, utile per evitare di ricreare l'HashMap a ogni modifica
     *
     * @param key    la chiave da aggiungere
     * @param values l'array di stringhe da aggiungere
     */
    public void add(String key, String[] values) {
        data.put(key, values);
    }

    /**
     * Ritorna {@code true} se l'UserID ha dei libri consigliati collegati
     *
     * @param UserID la chiave da cercare
     * @return se la chiave esiste
     */
    public boolean hasUser(String UserID) {
        return data.containsKey(UserID);
    }
}
