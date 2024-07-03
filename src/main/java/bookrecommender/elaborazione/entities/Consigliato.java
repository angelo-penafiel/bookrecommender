package bookrecommender.elaborazione.entities;

import java.util.List;

/**
 * Classe .
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class Consigliato {

  public static int MAX_LIBRI_CONSIGLIATI = 3;

  private String id;

  private String userId;

  private String libroId;

  private List<String> libriConsigliati;

  public Consigliato(String id, List<String> libriConsigliati) {
    this.id=id;
    this.libriConsigliati=libriConsigliati;
  }

  public Consigliato(String id, String userId, String libroId, List<String> libriConsigliati) {
    this.id=id;
    this.userId=userId;
    this.libroId=libroId;
    this.libriConsigliati=libriConsigliati;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getLibroId() {
    return libroId;
  }

  public void setLibroId(String libroId) {
    this.libroId = libroId;
  }

  public List<String> getLibriConsigliati() {
    return libriConsigliati;
  }

  public void setLibriConsigliati(List<String> libriConsigliati) {
    this.libriConsigliati = libriConsigliati;
  }
}
