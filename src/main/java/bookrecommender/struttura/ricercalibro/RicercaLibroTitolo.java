package bookrecommender.struttura.ricercalibro;

import bookrecommender.elaborazione.dao.LibroDao;
import bookrecommender.elaborazione.dao.daoimpl.LibroDaoImpl;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroTitoloMessaggi;
import java.io.IOException;
import java.util.List;

/**
 * Classe che ha la funzione di gestire la
 * sezione di ricerca del libro in base al
 * titolo inserito.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class RicercaLibroTitolo {

    //CAMPI

    /**
     * Campo che indica il libro trovato.
     */

    private Libro libro;

    /**
     * Campo che indica il titolo inserito dall'
     * utente.
     */

    private String titolo;

    /**
     * Campo che indica se tornare indietro
     */

    private boolean tornaIndietro;

    //COSTRUTTORE

    /**
     * Restituisce l'oggetto di tipo RicercaLibroTitolo
     * e gestisce la logica di ricerca del libro per
     * titolo.
     */

    public RicercaLibroTitolo() {

        NuovaSchermata.nuovaSchermata();
        RicercaLibroTitoloMessaggi.intestazioneInserimentoTitolo();

        titolo=RicercaLibroTitoloMessaggi.inserimentoTitolo();

        List<Integer> libriTrovati;
        try {
            LibroDao libroDao=new LibroDaoImpl();
            libriTrovati=libroDao.getIdsByTitolo(titolo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(!libriTrovati.isEmpty()) {

            tornaIndietro=false;

            Integer libroSelezionato= selezioneLibroTitolo(libriTrovati);

            try {
                LibroDao libroDao=new LibroDaoImpl();
                libro=libroDao.getById(libroSelezionato);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(libro.getTitolo());
            System.out.println(libro.getAutori());
            System.out.println(libro.getAnnoPubblicazione());
            System.out.println(libro.getEditore());
            System.out.println(libro.getCategorie());
        }

        else {
            RicercaLibroTitoloMessaggi.valoriNonTrovati();
            tornaIndietro=true;
        }
    }

    //METODI

    /**
     * Restituiscel'id del libro selezionato dall'utente
     * tra i risultati di ricerca ed effettua la selezione
     * da parte dell'utente del libro tra i risultati
     * trovati.
     *
     * @return l'id del libro selezionato dall'utente
     *         tra i risultati di ricerca
     */

    public static Integer selezioneLibroTitolo(List<Integer> libriTrovati) {

        List<Libro> opzioniTitoloAnno;

        Integer libroSelezionato;

        try {
            LibroDao libroDao=new LibroDaoImpl();
            opzioniTitoloAnno=libroDao.getTitoloAndAnnoByIds(libriTrovati);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NuovaSchermata.nuovaSchermata();

        if(opzioniTitoloAnno.size()>RicercaLibro.MAX_RISULTATI_PAGINA) {

            int paginaCorrente=0;
            boolean controllo;

            do {
                controllo=true;

                RicercaLibroTitoloMessaggi.stampaOpzioniTitoloAnnoPagina(opzioniTitoloAnno,paginaCorrente);
                libroSelezionato=SceltaMenuMessaggi.inserimentoSceltaOpzioniPagina(paginaCorrente,
                    opzioniTitoloAnno.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1,opzioniTitoloAnno.size());

                if(libroSelezionato==-1||libroSelezionato==-2) {
                    controllo=false;
                }

                if(libroSelezionato==-1) {
                    paginaCorrente=paginaCorrente+1;
                }

                if(libroSelezionato==-2) {
                    paginaCorrente=paginaCorrente-1;
                }

                NuovaSchermata.nuovaSchermata();

            } while (!controllo);
        }

        else {
            RicercaLibroTitoloMessaggi.stampaOpzioniTitoloAnno(opzioniTitoloAnno);
            libroSelezionato= SceltaMenuMessaggi.inserimentoSceltaMenu(opzioniTitoloAnno.size());
            NuovaSchermata.nuovaSchermata();
        }

        libroSelezionato=libriTrovati.get(libroSelezionato-1);

        return libroSelezionato;
    }

    /**
     * Restituisce il boolean che indica se
     * tornare indietro.
     *
     * @return il boolean se tornare indietro
     */

    public boolean isTornaIndietro() {
        return tornaIndietro;
    }
}
