package bookrecommender.elaborazione.entities.utils.singleton;

import bookrecommender.elaborazione.entities.utils.csvutils.CsvHasher;

import java.util.HashMap;

public class ValutazioniHashMap {
    private static ValutazioniHashMap instance;
    private HashMap<String, String[]> data;

    /**
     * Costruttore privato nel caso non esista già una vecchia istanza. Inizializza una nuova hashmap che verrà
     * condivisa "globalmente"
     */
    private ValutazioniHashMap() {
        data = new HashMap<>();
    }

    /**
     * Se esiste un istanza dell'oggetto, allora viene ritornata quella istanza, altrimenti viene inizializzata
     * la prima istanza
     *
     * @return instance, l'istanza corrente (vecchia o nuova)
     */
    public static ValutazioniHashMap getInstance() {
        if (instance == null) {
            instance = new ValutazioniHashMap();
            instance.init();
        }
        return instance;
    }

    String[] headers = {"UserID", "Libro", "Stile", "tStile", "Contenuto", "tContenuto", "Gradevolezza", "tGradevolezza", "Originalita",
            "tOriginalita", "Edizione", "tEdizione", "Finale", "tFinale"
    };

    /**
     * Questo metodo privato serve a inizializzare la hashmap
     */
    private void init() {

        String path = "data/ValutazioniLibri.dati.csv";

        data = CsvHasher.hashCsv("UserID", headers, path);
    }

    /**
     * Dato un UserID, questa funzione ritorna tutti i dati dell'utente a
     * cui è associato l'id in un'Array
     *
     * @param UserID La chiave della HashMap
     * @return l'array con i valori legati alla chiave
     */
    public String[] getValues(String UserID, String libro) {
        for (String key : data.keySet()) {
            String[] values = data.get(key);
            if (key.equals(UserID) && values[1].equals(libro)) {
                return values;
            }
        }
        return null;
    }
    public String[] getHeaders() {return headers;}
    /**
     * Ritorna il Nome associato a un UserID.
     *
     * @param userID
     * @return Il Nome associato al UserID.
     */
    public String getUserID(String userID) {
        String[] userData = data.get(userID);
        if (userData != null) {
            return userData[0]; // Nome
        }
        return null;
    }

    /**
     * Ritorna il Cognome associato a un UserID dall'HashMap.
     *
     * @param userID
     * @return Il Cognome associato al UserID.
     */
    public String getLibro(String userID) {
        String[] userData = data.get(userID);
        if (userData != null) {
            return userData[1]; // Cognome
        }
        return null;
    }

    /**
     * Controlla se esiste giä nel database la review
     */
    public boolean hasValutazione(String userID, String libro) {
        for (String key : data.keySet()) {
            String[] values = data.get(key);
            if (key.equals(userID) && values[1].equals(libro)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ritorna {@code true} se esiste l'utente associato all'UserID in input
     *
     * @param UserID la chiave da cercare
     * @return se la chiave esiste
     */
    public boolean hasUser(String UserID) {
        return data.containsKey(UserID);
    }

    /**
     * Aggiunge una valutazione all'HashMap, utile per evitare di ricreare l'HashMap a ogni modifica
     *
     * @param key    la chiave da aggiungere
     * @param values l'array di stringhe da aggiungere
     */
    public void add(String key, String[] values) {
        data.put(key, values);
    }

    public void remove(String key) {
        data.remove(key);
    }
}
