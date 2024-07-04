package bookrecommender.elaborazione.entities.utils.singleton;

import bookrecommender.elaborazione.entities.utils.csvutils.CsvHasher;

import java.util.HashMap;

/**
 * Crea un hashmap delle valutazioni usando il pattern Singleton.
 * L'obbiettivo è migliorare le performance in fase di ricerca
 *
 * @author Leonardo Basso
 */

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

    /**
     * Questo metodo privato serve a inizializzare la hashmap
     */
    private void init() {
        String[] headers = {"UserID", "Libro", "Stile", "tStile", "Contenuto", "tContenuto", "Gradevolezza", "tGradevolezza", "Originalita",
                "tOriginalita", "Edizione", "tEdizione", "Finale", "tFinale"
        };

        String path = "data/ValutazioniLibri.dati.csv";

        data = CsvHasher.hashCsv("UserID","Libro", headers, path);
    }

    /**
     * Dato un UserID, questa funzione ritorna tutti i dati dell'utente a
     * cui è associato l'id in un'Array
     *
     * @param UserID La chiave della HashMap
     * @param libro il libro da ottenere
     * @return l'array con i valori legati alla chiave
     */
    public String[] getValues(String UserID, String libro) {
        return data.get(UserID + "-" + libro);
    }

    /**
     * Controlla se esiste giä nel database la review
     * @param UserID l'utente che sta usando il programma
     * @param libro il libro da cercare
     */
    public boolean hasValutazione(String UserID, String libro) {
        return data.containsKey(UserID + "-" + libro);
    }

    /**
     * Aggiunge una valutazione all'HashMap, utile per evitare di ricreare l'HashMap a ogni modifica
     *
     * @param libro  la chiave da aggiungere
     * @param UserID la seconda chiave
     * @param values l'array di stringhe da aggiungere
     */
    public void add(String UserID, String libro, String[] values) {
        data.put(UserID + "-" + libro, values);
    }
}
