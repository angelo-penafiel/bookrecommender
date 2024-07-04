package bookrecommender.elaborazione.entities;

/**
 * Classe che rappresenta l'oggetto di tipo
 * Valutazione. La logica di business non viene
 * contemplata nei suoi metodi e rappresenta
 * con i suoi campi l'oggetto di tipo valutazione
 * contenuto nel file ValutazioniLibri.dati.csv.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class Valutazione {


  //CAMPI

  /**
   * Campo che rappresenta l'id dell'utente.
   */

  private String userId;

  /**
   * Campo che rappresenta l'id del libro.
   */

  private String libroId;

  /**
   * Campo che rappresenta lo stile.
   */

  private Double stile;

  /**
   * Campo che rappresenta il contenuto.
   */

  private Double contenuto;

  /**
   * Campo che rappresenta la gradevolezza.
   */

  private Double gradevolezza;

  /**
   * Campo che rappresenta la originalita.
   */

  private Double originalita;

  /**
   * Campo che rappresenta l'edizione.
   */

  private Double edizione;

  /**
   * Campo che rappresenta il finale.
   */

  private Double finale;


  //METODI COSTRUTTORI

  /**
   * Costruisce un oggetto che rappresenta la
   * valutazione di cui vengono forniti solamente i
   * valori dei criteri. Il metodo viene utilizzato
   * nel metodo costruttore di VisualizzazioneLibro.
   *
   * @param stile rappresenta lo stile
   *
   * @param contenuto rappresenta il contenuto
   *
   * @param gradevolezza rappresenta la gradevolezza
   *
   * @param originalita rappresenta l'originalità
   *
   * @param edizione rappresenta l'edizione
   *
   * @param finale rappresenta il finale
   */

  public Valutazione(Double stile, Double contenuto, Double gradevolezza,
      Double originalita, Double edizione, Double finale) {

    this.stile=stile;
    this.contenuto=contenuto;
    this.gradevolezza=gradevolezza;
    this.originalita=originalita;
    this.edizione=edizione;
    this.finale=finale;

  }

  /**
   * Costruisce un oggetto che rappresenta la
   * valutazione.
   *
   * @param userId rappresenta l'utente
   *
   * @param libroId rappresenta l'id del libro
   *
   * @param stile rappresenta lo stile
   *
   * @param contenuto rappresenta il contenuto
   *
   * @param gradevolezza rappresenta la gradevolezza
   *
   * @param originalita rappresenta l'originalità
   *
   * @param edizione rappresenta l'edizione
   *
   * @param finale rappresenta il finale
   */

  public Valutazione(String userId, String libroId, Double stile,
      Double contenuto, Double gradevolezza, Double originalita,
      Double edizione, Double finale) {

    this.userId=userId;
    this.libroId=libroId;
    this.stile=stile;
    this.contenuto=contenuto;
    this.gradevolezza=gradevolezza;
    this.originalita=originalita;
    this.edizione=edizione;
    this.finale=finale;

  }


  //METODI

  /**
   * Restituisce l'ide dell'utente
   * @return l'utente
   */

  public String getUserId() {
    return userId;
  }

  /**
   * Setta l'id dell'utente
   * @param userId rappresenta l'utente
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
   * Restituisce lo stile
   * @return lo stile
   */

  public Double getStile() {
    return stile;
  }

  /**
   * Setta lo stile
   * @param stile rappresenta lo stile
   */

  public void setStile(Double stile) {
    this.stile = stile;
  }

  /**
   * Restituisce il contenuto
   * @return il contenuto
   */

  public Double getContenuto() {
    return contenuto;
  }

  /**
   * Setta il contenuto
   * @param contenuto rappresenta il contenuto
   */

  public void setContenuto(Double contenuto) {
    this.contenuto = contenuto;
  }

  /**
   * Restituisce la gradevolezza
   * @return la gradevolezza
   */

  public Double getGradevolezza() {
    return gradevolezza;
  }

  /**
   * Setta la gradevolezza
   * @param gradevolezza rappresenta la gradevolezza
   */

  public void setGradevolezza(Double gradevolezza) {
    this.gradevolezza = gradevolezza;
  }

  /**
   * Restituisce l'originalità
   * @return l'originalità
   */

  public Double getOriginalita() {
    return originalita;
  }

  /**
   * Setta l'originalità
   * @param originalita rappresenta l'originalità
   */

  public void setOriginalita(Double originalita) {
    this.originalita = originalita;
  }

  /**
   * Restituisce l'edizione
   * @return l'edizione
   */

  public Double getEdizione() {
    return edizione;
  }

  /**
   * Setta l'edizione
   * @param edizione rappresenta l'edizione
   */

  public void setEdizione(Double edizione) {
    this.edizione = edizione;
  }

  /**
   * Restituisce il finale
   * @return il finale
   */

  public Double getFinale() {
    return finale;
  }

  /**
   * Setta il finale
   * @param finale rappresenta il finale
   */

  public void setFinale(Double finale) {
    this.finale = finale;
  }

}
