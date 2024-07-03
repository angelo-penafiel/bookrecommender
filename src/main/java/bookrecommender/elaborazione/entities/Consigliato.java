package bookrecommender.elaborazione.entities;

/**
 * Classe .
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class Consigliato {

  public static int MAX_LIBRI_CONSIGLIATI = 3;

  private String userId;

  private String[] libriConsigliati;

  public Consigliato(String userId, String[] libriConsigliati) {
    this.userId=userId;
    this.libriConsigliati=libriConsigliati;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String[] getLibriConsigliati() {
    return libriConsigliati;
  }

  public void setLibriConsigliati(String[] libriConsigliati) {
    this.libriConsigliati = libriConsigliati;
  }
}
