
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.entities;

import java.util.List;

public class Libro {

    public static final Integer MAX_TITOLO=200;

    public static final Integer MIN_TITOLO=3;

    public static final Integer MAX_AUTORE_CARATTERI=50;

    public static final Integer MIN_AUTORE_CARATTERI=2;

    public static final Integer MIN_ANNO_PUBBLICAZIONE=1755;

    private String titolo;

    private List<String> autori;

    private Integer annoPubblicazione;

    private String editore;

    private List<String> categorie;

    public Libro(String titolo, List<String> autori, Integer annoPubblicazione, String editore,
                 List<String> categorie) {
        this.titolo =titolo;
        this.autori=autori;
        this.annoPubblicazione=annoPubblicazione;
        this.editore =editore;
        this.categorie=categorie;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public List<String> getAutori() {
        return autori;
    }

    public void setAutori(List<String> autori) {
        this.autori = autori;
    }

    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public String getEditore() {
        return editore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public List<String> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<String> categorie) {
        this.categorie = categorie;
    }


}
