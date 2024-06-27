package bookrecommender.elaborazione.entities;

/**
 * Classe che rappresenta l'oggetto di tipo
 * libreria. La logica di business non viene
 * contemplata nei suoi metodi e rappresenta
 * con i suoi campi l'oggetto di tipo libreria
 * contenuto nel file Libreria.dati.csv.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class Libreria {

    //CAMPI

    /**
     * Costante che rappresenta il numero massimo
     * di caratteri del nome.
     */

    public static final Integer MAX_NOME=50;

    /**
     * Costante che rappresenta il numero minimo
     * di caratteri del nome.
     */

    public static final Integer MIN_NOME=3;

    /**
     * Campo che rappresenta l'id.
     */

    private Integer id;

    /**
     * Campo che rappresenta il nome.
     */

    private String nome;

    /**
     * Campo che rappresenta lo userId
     * a cui appartiene la libreria.
     */

    private String userId;


    //COSTRUTTORI

    /**
     * Costruisce un oggetto che rappresenta la libreria.
     *
     * @param id l'intero che rappresenta l'id
     *
     * @param nome la stringa che rappresenta il nome
     *
     * @param userId l'intero che rappresenta l'id dell'utente
     *
     */

    public Libreria(Integer id, String nome, String userId) {
        this.id=id;
        this.nome=nome;
        this.userId=userId;
    }

    //METODI


    /**
     * Restituisce l'id
     * @return l'id
     */

    public Integer getId() {
        return id;
    }

    /**
     * Setta l'id
     * @param id rappresenta l'id
     */

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Restituisce il nome
     * @return il nome
     */

    public String getNome() {
        return nome;
    }

    /**
     * Setta il nome
     * @param nome rappresenta il nome
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce lo userId
     * @return lo userId
     */

    public String getUserId() {
        return userId;
    }

    /**
     * Setta lo userId
     * @param userId rappresenta lo userId
     */

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
