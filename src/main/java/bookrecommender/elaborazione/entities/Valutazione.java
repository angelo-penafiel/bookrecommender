package bookrecommender.elaborazione.entities;

public class Valutazione {

  private String userId;

  private String libroId;

  private Double stile;

  private Double contenuto;

  private Double gradevolezza;

  private Double originalita;

  private Double edizione;

  private Double finale;

  public Valutazione(Double stile, Double contenuto, Double gradevolezza,
      Double originalita, Double edizione, Double finale) {

    this.stile=stile;
    this.contenuto=contenuto;
    this.gradevolezza=gradevolezza;
    this.originalita=originalita;
    this.edizione=edizione;
    this.finale=finale;

  }

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

  public Double getStile() {
    return stile;
  }

  public void setStile(Double stile) {
    this.stile = stile;
  }

  public Double getContenuto() {
    return contenuto;
  }

  public void setContenuto(Double contenuto) {
    this.contenuto = contenuto;
  }

  public Double getGradevolezza() {
    return gradevolezza;
  }

  public void setGradevolezza(Double gradevolezza) {
    this.gradevolezza = gradevolezza;
  }

  public Double getOriginalita() {
    return originalita;
  }

  public void setOriginalita(Double originalita) {
    this.originalita = originalita;
  }

  public Double getEdizione() {
    return edizione;
  }

  public void setEdizione(Double edizione) {
    this.edizione = edizione;
  }

  public Double getFinale() {
    return finale;
  }

  public void setFinale(Double finale) {
    this.finale = finale;
  }

}
