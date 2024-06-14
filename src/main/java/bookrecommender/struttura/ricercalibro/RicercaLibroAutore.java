package bookrecommender.struttura.ricercalibro;

import bookrecommender.elaborazione.dao.LibroDao;
import bookrecommender.elaborazione.dao.daoimpl.LibroDaoImpl;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroAutoreMessaggi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che ha la funzione di gestire la
 * sezione di ricerca del libro in base all'
 * autore inserito.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class RicercaLibroAutore {

    //CAMPI

    /**
     * Campo che indica il libro trovato.
     */

    private Libro libro;

    /**
     * Campo che indica l'autore inserito dall'
     * utente.
     */

    private String autore;

    /**
     * Campo che indica la lista di autori
     * correlati all'autore inserito.
     */

    private List<String> autoriTrovati;

    /**
     * Campo che indica l'id del libro selezionato
     * dall'utente tra i risultati di ricerca.
     */

    private Integer libroSelezionato;

    /**
     * Campo che indica l'autore selezionato
     * dall'utente tra i risultati di ricerca.
     */

    private String autoreSelezionato;

    /**
     * Campo che indica se tornare indietro
     */

    private boolean tornaIndietro;

    //COSTRUTTORE

    /**
     * Restituisce l'oggetto di tipo
     * RicercaLibroAutore e gestisce la logica
     * di ricerca del libro per autore.
     */

    public RicercaLibroAutore() {

        NuovaSchermata.nuovaSchermata();
        RicercaLibroAutoreMessaggi.intestazioneInserimentoAutore();

        autore=RicercaLibroAutoreMessaggi.inserimentoAutore();

        autoriTrovati=new ArrayList<>();
        try {
            LibroDao libroDao=new LibroDaoImpl();
            autoriTrovati=libroDao.getAutoriByAutore(autore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(!autoriTrovati.isEmpty()) {

            tornaIndietro=false;

            Integer autoreSelezionatoInt;
            NuovaSchermata.nuovaSchermata();

            if(autoriTrovati.size()>RicercaLibro.MAX_RISULTATI_PAGINA) {

                int paginaCorrente=0;
                boolean controllo;

                do {
                    controllo=true;

                    RicercaLibroAutoreMessaggi.stampaOpzioniAutorePagina(autoriTrovati,paginaCorrente);
                    autoreSelezionatoInt=SceltaMenuMessaggi.inserimentoSceltaOpzioniPagina(paginaCorrente,
                        autoriTrovati.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1,autoriTrovati.size());

                    if(autoreSelezionatoInt==-1||autoreSelezionatoInt==-2) {
                        controllo=false;
                    }

                    if(autoreSelezionatoInt==-1) {
                        paginaCorrente=paginaCorrente+1;
                    }

                    if(autoreSelezionatoInt==-2) {
                        paginaCorrente=paginaCorrente-1;
                    }
                    NuovaSchermata.nuovaSchermata();

                } while (!controllo);
            }

            else {
                RicercaLibroAutoreMessaggi.stampaOpzioniAutore(autoriTrovati);
                autoreSelezionatoInt= SceltaMenuMessaggi.inserimentoSceltaMenu(autoriTrovati.size());
                NuovaSchermata.nuovaSchermata();
            }

            autoreSelezionato=autoriTrovati.get(autoreSelezionatoInt-1);

            List<Integer> libriTrovati;

            try {
                LibroDao libroDao=new LibroDaoImpl();
                libriTrovati=libroDao.getIdsByAutore(autoreSelezionato);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            libroSelezionato=RicercaLibroTitolo.selezioneLibroTitolo(libriTrovati);

            try {
                LibroDao libroDao=new LibroDaoImpl();
                libro=libroDao.getById(libroSelezionato);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        else {
            RicercaLibroAutoreMessaggi.valoriNonTrovati();
            tornaIndietro=true;
        }

    }

    //METODO

    /**
     * Restituisce il boolean che indica se
     * tornare indietro.
     *
     * @return il boolean se tornare indietro
     */

    public boolean isTornaIndietro() {
        return tornaIndietro;
    }

    public Libro getLibro() {
        return libro;
    }
}
