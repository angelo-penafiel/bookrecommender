package bookrecommender.elaborazione.entities;

import java.util.List;

/**
 * Classe che rappresenta l'oggetto di tipo
 * Consigliato. La logica di business non viene
 * contemplata nei suoi metodi e rappresenta
 * con i suoi campi l'oggetto di tipo consigliato
 * contenuto nel file ConsigliLibri.dati.csv e
 * AssegnamentoConsigliatiLibri.dati.csv.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class Consigliato {


  //CAMPI

  /**
   * Costante che rappresenta il numero massimo
   * di libri consigliati per libro corrente.
   */

  public static int MAX_LIBRI_CONSIGLIATI = 3;

  /**
   * Campo che rappresenta l'id.
   */

  private String id;

  /**
   * Campo che rappresenta l'id dell'utente.
   */

  private String userId;

  /**
   * Campo che rappresenta l'id del
   * libro corrente.
   */

  private String libroId;

  /**
   * Campo che rappresenta gli id dei libri
   * consigliati.
   */

  private List<String> libriConsigliati;


  //METOFDO COSTRUTTORE

  public Consigliato(String id, String userId, String libroId, List<String> libriConsigliati) {
    this.id=id;
    this.userId=userId;
    this.libroId=libroId;
    this.libriConsigliati=libriConsigliati;
  }


  //METODI

  /**
   * Restituisce l'id
   * @return l'id
   */

  public String getId() {
    return id;
  }

  /**
   * Setta l'id
   * @param id rappresenta l'id
   */

  public void setId(String id) {
    this.id = id;
  }

  /**
   * Restituisce l'id dell'utente
   * @return l'id dell'utente
   */

  public String getUserId() {
    return userId;
  }

  /**
   * Setta l'id dell'utente
   * @param userId rappresenta l'id dell'utente
   */

  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * Restituisce l'id del libro
   * @return l'id del libro
   */

  public String getLibroId() {
    return libroId;
  }

  /**
   * Setta l'id del libro
   * @param libroId rappresenta l'id del libro
   */

  public void setLibroId(String libroId) {
    this.libroId = libroId;
  }

  /**
   * Restituisce gli id dei libri consigliati
   * @return gli id dei libri consigliati
   */

  public List<String> getLibriConsigliati() {
    return libriConsigliati;
  }

  /**
   * Setta gli id dei libri consigliati
   * @param libriConsigliati rappresenta gli
   *                         id dei libri
   *                         consigliati
   */

  public void setLibriConsigliati(List<String> libriConsigliati) {
    this.libriConsigliati = libriConsigliati;
  }

}
