package bookrecommender.elaborazione.entities;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Classe che rappresenta l'oggetto di tipo Libro.
 * La logica di business non viene contemplata nei
 * suoi metodi e rappresenta con i suoi campi
 * l'oggetto di tipo libro contenuto nel file
 * Libri.dati.csv.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class Libro {

    //CAMPI

    /**
     * Costante che rappresenta il numero massimo
     * di caratteri del titolo.
     */

    public static final Integer MAX_TITOLO=200;

    /**
     * Costante che rappresenta il numero minimo
     * di caratteri del titolo.
     */

    public static final Integer MIN_TITOLO=3;

    /**
     * Costante che rappresenta il numero massimo
     * di caratteri dell'autore.
     */

    public static final Integer MAX_AUTORE_CARATTERI=50;

    /**
     * Costante che rappresenta il numero minimo
     * di caratteri dell'autore.
     */

    public static final Integer MIN_AUTORE_CARATTERI=2;

    /**
     * Costante che rappresenta l'anno massimo
     * di pubblicazione.
     */

    public static final Integer MAX_ANNO_PUBBLICAZIONE = LocalDateTime.now().getYear();

    /**
     * Costante che rappresenta l'anno minimo
     * di pubblicazione.
     */

    public static final Integer MIN_ANNO_PUBBLICAZIONE=1755;

    private Integer id;

    /**
     * Campo che rappresenta il titolo.
     */

    private String titolo;

    /**
     * Campo che rappresenta la lista di
     * autori.
     */

    private List<String> autori;

    /**
     * Campo che rappresenta l'anno di
     * pubblicazione.
     */

    private Integer annoPubblicazione;

    /**
     * Campo che rappresenta l'editore.
     */

    private String editore;

    /**
     * Campo che rappresenta la lista di
     * categorie.
     */

    private List<String> categorie;

    //COSTRUTTORI

    /**
     * Costruisce un oggetto che rappresenta il libro
     * di cui vengono forniti solamente il titolo e
     * l'anno di pubblicazione. Il metodo viene utilizzato
     * nel metodo getTitoloAndAnnoByIds di LibroDaoImpl.
     *
     * @param titolo la stringa che rappresenta il titolo
     *
     * @param annoPubblicazione l'intero che rappresenta
     *                          l'anno di pubblicazione
     */

    public Libro(String titolo, Integer annoPubblicazione) {
        this.titolo =titolo;
        this.annoPubblicazione=annoPubblicazione;
    }

    /**
     * Costruisce un oggetto che rappresenta il libro
     * di cui vengono forniti solamente il titolo e
     * l'autore. Il metodo viene utilizzato nel metodo
     * getAutoreAndTitoloByAutoriAndAnno di LibroDaoImpl.
     *
     * @param titolo la stringa che rappresenta il titolo
     *
     * @param autore la stringa che rappresenta l'autore
     */

    public Libro(String titolo, String autore) {
        this.titolo = titolo;
        this.autori= Collections.singletonList(autore);
    }

    public Libro(Integer id, String titolo, List<String> autori, Integer annoPubblicazione, String editore,
                 List<String> categorie) {
        this.id=id;
        this.titolo=titolo;
        this.autori=autori;
        this.annoPubblicazione=annoPubblicazione;
        this.editore =editore;
        this.categorie=categorie;
    }

    //METODI


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Restituisce il titolo
     * @return il titolo
     */

    public String getTitolo() {
        return titolo;
    }

    /**
     * Setta il titolo
     * @param titolo rappresenta il titolo
     */

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Restituisce gli autori
     * @return gli autori
     */

    public List<String> getAutori() {
        return autori;
    }

    /**
     * Setta gli autori
     * @param autori rappresenta gli autori
     */

    public void setAutori(List<String> autori) {
        this.autori = autori;
    }

    /**
     * Restituisce l'anno di pubblicazione
     * @return l'anno di pubblicazione
     */

    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    /**
     * Setta l'anno di pubblicazione
     * @param annoPubblicazione rappresenta l'anno di
     *                          pubblicazione
     */

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    /**
     * Restituisce l'editore
     * @return l'editore
     */

    public String getEditore() {
        return editore;
    }

    /**
     * Setta l'editore
     * @param editore rappresenta l'editore
     */

    public void setEditore(String editore) {
        this.editore = editore;
    }

    /**
     * Restituisce le categorie
     * @return le categorie
     */

    public List<String> getCategorie() {
        return categorie;
    }

    /**
     * Setta le categorie
     * @param categorie rappresenta le categorie
     */

    public void setCategorie(List<String> categorie) {
        this.categorie = categorie;
    }

}
