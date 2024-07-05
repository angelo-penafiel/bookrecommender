package bookrecommender.elaborazione.dao;

import bookrecommender.elaborazione.entities.Libro;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Interfaccia che implementa il design pattern DAO.
 * La sua funzione è quella di separare la logica
 * di accesso ai dati dalla logica di business.
 * Ha lo scopo di prelevare i dati del file csv
 * (Libri.dati.csv) e creare oggetti o campi di classe
 * Libro.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public interface LibroDao {

    //METODI

    /**
     * Restituisce una lista di id dei libri correlati
     * dato il titolo del libro cercato.
     *
     * @param titolo indica il titolo
     *
     * @return lista di id correlati al titolo
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    List<Integer> getIdsByTitolo(String titolo) throws IOException;

    /**
     * Restituisce una lista di oggetti di tipo
     * libro, di cui sono forniti solamente i
     * campi titolo e anno di pubblicazione,
     * dato una lista di id di libri.
     *
     * @param libriId indica la lista di libri
     *
     * @return lista di titoli e anno di pubblicazione
     *         cercati
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    List<Libro> getTitoloAndAnnoByIds(List<Integer> libriId) throws IOException;

    /**
     * Restituisce il titolo del libro dato l'id.
     *
     * @param id indica l'id del libro
     *
     * @return il titolo
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    String getTitoloById(Integer id) throws IOException;

    /**
     * Restituisce una lista di autori correlati
     * al nome dell'autore fornito.
     *
     * @param autore indica l'autore di cui bisogna
     *               effettuare la ricerca
     *
     * @return lista di autori cercati
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    List<String> getAutoriByAutore(String autore) throws IOException;

    /**
     * Restituisce la lista di autori presenti
     * all'interno di tutto il file Libri.dati.csv.
     * Nella lista non sono presenti duplicati.
     *
     * @return hashmap degli autori globali
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    HashMap<String, Integer> getAllAutori() throws IOException;

    /**
     * Restituisce una lista di id di libri
     * correlati al nome dell'autore fornito.
     *
     * @param autore indica l'autore di cui bisogna
     *               effettuare la ricerca
     *
     * @return lista di id di libri cercati
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    List<Integer> getIdsByAutore(String autore) throws IOException;

    /**
     * Restituisce una lista di oggetti di tipo
     * libro, di cui sono forniti solamente i
     * campi dell'autore e del titolo, correlati
     * agli autori e all'anno di pubblicazioni
     * forniti.
     *
     * @param autori indica gli autori di cui
     *               bisogna effettuare la ricerca
     *
     * @param annoPubblicazione indica l'anno di
     *                          pubblicazione di
     *                          cui bisogna ef-
     *                          -fettuare la ri-
     *                          -cerca
     *
     * @return lista di autori e titoli cercati
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    List<Libro> getAutoreAndTitoloByAutoriAndAnno(List<String> autori,
        Integer annoPubblicazione) throws IOException;

    /**
     * Restituisce l'id del libro correlato al
     * titolo fornito.
     *
     * @param titolo indica il titolo di cui
     *               bisogna effettuare la
     *               ricerca
     *
     * @return id del libro cercato
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    Integer getIdByTitolo(String titolo) throws IOException;

    /**
     * Restituisce l'oggetti di tipo libro
     * correlato all'id.
     *
     * @param id indica l'id del libro di cui
     *           bisogna effettuare la ricerca
     *
     * @return libro correlato all'id
     *
     * @throws IOException nel caso in cui si
     * verifica un errore in fase di apertura
     * e scrittura dei file
     */

    Libro getById(Integer id) throws IOException;
}
