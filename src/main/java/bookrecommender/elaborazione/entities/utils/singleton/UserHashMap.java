package bookrecommender.elaborazione.entities.utils.singleton;

import bookrecommender.elaborazione.entities.utils.csvutils.CsvHasher;

import java.util.HashMap;

/**
 * Crea un hashmap degli utenti usando il pattern Singleton.
 * L'obbiettivo è migliorare le performance in fase di ricerca
 *
 * @author Leonardo Basso
 * @see bookrecommender.elaborazione.entities.user.User
 */
public class UserHashMap {
    private static UserHashMap instance;
    private HashMap<String, String[]> data;

    /**
     * Costruttore privato nel caso non esista già una vecchia istanza. Inizializza una nuova hashmap che verrà
     * condivisa "globalmente"
     */
    private UserHashMap() {
        data = new HashMap<>();
    }

    /**
     * Se esiste un istanza dell'oggetto, allora viene ritornata quella istanza, altrimenti viene inizializzata
     * la prima istanza
     *
     * @return instance, l'istanza corrente (vecchia o nuova)
     */
    public static UserHashMap getInstance() {
        if (instance == null) {
            instance = new UserHashMap();
            instance.init();
        }
        return instance;
    }

    /**
     * Questo metodo privato serve a inizializzare la hashmap
     */
    private void init() {
        String[] headers = {"Nome", "Cognome", "UserID", "Taxcode", "Mail", "Password"};
        String path = "data/Database/UtentiRegistrati.dati.csv";

        data = CsvHasher.hashCsv("UserID", headers, path);
    }

    /**
     * Dato un UserID, questa funzione ritorna tutti i dati dell'utente a
     * cui è associato l'id in un'Array
     *
     * @param UserID La chiave della HashMap
     * @return l'array con i valori legati alla chiave
     */
    public String[] getValues(String UserID) {
        return data.get(UserID);
    }

    /**
     * Ritorna il Nome associato a un UserID.
     * @param userID
     * @return Il Nome associato al UserID.
     */
    public String getNome(String userID) {
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
    public String getCognome(String userID) {
        String[] userData = data.get(userID);
        if (userData != null) {
            return userData[1]; // Cognome
        }
        return null;
    }

    /**
     * Ritorna il Codice Fiscale associato a un UserID
     *
     * @param userID
     * @return Il Codice Fiscale associato al UserID.
     */
    public String getTaxcode(String userID) {
        String[] userData = data.get(userID);
        if (userData != null) {
            return userData[3]; // Taxcode
        }
        return null;
    }

    /**
     * Ritorna la Mail associata a un UserID.
     *
     * @param userID
     * @return La Mail associata al UserID.
     */
    public String getMail(String userID) {
        String[] userData = data.get(userID);
        if (userData != null) {
            return userData[4]; // Mail
        }
        return null;
    }

    /**
     * Ritorna la Password criptata associata a un UserID.
     *
     * @param userID
     * @return La Password criptata associata al UserID.
     */
    public String getPassword(String userID) {
        String[] userData = data.get(userID);
        if (userData != null) {
            return userData[5]; // Password
        }
        return null;
    }

    /**
     * Aggiunge un utente all'HashMap, utile per evitare di ricreare l'HashMap a ogni modifica
     *
     * @param key    la chiave da aggiungere
     * @param values l'array di stringhe da aggiungere
     */
    public void add(String key, String[] values) {
        data.put(key, values);
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
}
