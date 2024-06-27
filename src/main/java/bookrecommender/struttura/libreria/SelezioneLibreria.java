package bookrecommender.struttura.libreria;

import bookrecommender.elaborazione.dao.LibreriaDao;
import bookrecommender.elaborazione.dao.daoimpl.LibreriaDaoImpl;
import bookrecommender.elaborazione.entities.Libreria;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.libreria.SelezioneLibreriaMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import java.io.IOException;
import java.util.List;

/**
 * Classe che ha la funzione di gestire la
 * sezione di selezione della libreria.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class SelezioneLibreria {

  //CAMPI

  /**
   * Costante che indica il numero massimo
   * di risultati stampati per pagina.
   */

  public static final Integer MAX_RISULTATI_PAGINA=7;

  /**
   * Campo che indica la libreria
   * selezionata dall'utente.
   */

  private Libreria libreria;

  //COSTRUTTORE

  /**
   * Restituisce l'oggetto di tipo SelezioneLibreria
   * e all'interno viene effettuata la stampa
   * delle librerie, dati gli id delle librerie trovate,
   * e l'inserimento della scelta della libreria da
   * selezionare.
   *
   * @param idsLibrerieTrovate indica la lista di id delle
   *                           librerie trovate dell'utente
   *                           loggato
   */

  public SelezioneLibreria(List<Integer> idsLibrerieTrovate) {

    List<String> nomiLibrerieTrovate;

    LibreriaDao libreriaDao=new LibreriaDaoImpl();
    try {
      nomiLibrerieTrovate=libreriaDao.getNomiByIds(idsLibrerieTrovate);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Integer idLibreriaSelezionata;

    NuovaSchermata.nuovaSchermata();

    if(nomiLibrerieTrovate.size() > MAX_RISULTATI_PAGINA) {

      int paginaCorrente=0;
      boolean controllo;

      do {
        controllo=true;

        SelezioneLibreriaMessaggi.stampaOpzioniNomePagina(nomiLibrerieTrovate,paginaCorrente);
        idLibreriaSelezionata = SceltaMenuMessaggi.inserimentoSceltaOpzioniPagina(paginaCorrente,
            nomiLibrerieTrovate.size()/MAX_RISULTATI_PAGINA+1,nomiLibrerieTrovate.size());

        if(idLibreriaSelezionata==-1||idLibreriaSelezionata==-2) {
          controllo=false;
        }

        if(idLibreriaSelezionata==-1) {
          paginaCorrente=paginaCorrente+1;
        }

        if(idLibreriaSelezionata==-2) {
          paginaCorrente=paginaCorrente-1;
        }

        NuovaSchermata.nuovaSchermata();

      } while (!controllo);
    }

    else {
      SelezioneLibreriaMessaggi.stampaOpzioniNome(nomiLibrerieTrovate);
      idLibreriaSelezionata = SceltaMenuMessaggi.inserimentoSceltaMenu(nomiLibrerieTrovate.size());
      NuovaSchermata.nuovaSchermata();
    }

    idLibreriaSelezionata=idsLibrerieTrovate.get(idLibreriaSelezionata-1);

    try {
      libreria=libreriaDao.getById(idLibreriaSelezionata);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  //METODO

  /**
   * Restituisce la libreria
   * selezionata dell'utente.
   *
   * @return la libreria
   */

  public Libreria getLibreria() {
    return libreria;
  }
}
