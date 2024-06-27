package bookrecommender.struttura.ricercalibro;

import bookrecommender.elaborazione.dao.LibroDao;
import bookrecommender.elaborazione.dao.daoimpl.LibroDaoImpl;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroAutoreAnnoMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroAutoreMessaggi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che ha la funzione di gestire la
 * sezione di ricerca del libro in base all'
 * autore e all'anno di pubblicazione inseriti.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class RicercaLibroAutoreAnnoPubblicazione {

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
     * Campo che indica l'anno di pubblicazione
     * inserito dall'utente.
     */

    private Integer annoPubblicazione;

    /**
     * Campo che indica la lista di autori
     * correlati all'autore inserito.
     */

    private List<String> autoriTrovati;

    /**
     * Campo che indica la lista di oggetti
     * di tipo libro contenente solamente
     * l'autore e il titolo dei libri trovati.
     */

    private List<Libro> autoreTitolo;

    /**
     * Campo che indica il titolo selezionato
     * dall'utente tra i risultati di ricerca.
     */

    private String titoloSelezionato;

    /**
     * Campo che indica l'id del libro selezionato
     * dall'utente tra i risultati di ricerca.
     */
    
    private Integer libroSelezionato;

    /**
     * Campo che indica se tornare indietro
     */

    private boolean tornaIndietro;

    //COSTRUTTORE

    /**
     * Restituisce l'oggetto di tipo
     * RicercaLibroAutoreAnnoPubblicazione e gestisce
     * la logica di ricerca del libro per autore e
     * anno di pubblicazione.
     */

    public RicercaLibroAutoreAnnoPubblicazione() {

        //ci sono più angela 2006

        NuovaSchermata.nuovaSchermata();
        RicercaLibroAutoreAnnoMessaggi.intestazioneInserimentoAutoreAnno();

        autore= RicercaLibroAutoreMessaggi.inserimentoAutore();

        annoPubblicazione=RicercaLibroAutoreAnnoMessaggi.inserimentoAnnoPubblicazione();

        autoriTrovati=new ArrayList<>();
        try {
            LibroDao libroDao=new LibroDaoImpl();
            autoriTrovati=libroDao.getAutoriByAutore(autore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        autoreTitolo=new ArrayList<>();
        try {
            LibroDao libroDao=new LibroDaoImpl();
            autoreTitolo=libroDao.getAutoreAndTitoloByAutoriAndAnno(autoriTrovati,annoPubblicazione);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(!autoreTitolo.isEmpty()) {

            tornaIndietro=false;

            Integer titoloSelezionatoInt;
            NuovaSchermata.nuovaSchermata();

            if(autoreTitolo.size()>RicercaLibro.MAX_RISULTATI_PAGINA) {

                int paginaCorrente=0;
                boolean controllo;

                do {
                    controllo=true;

                    RicercaLibroAutoreAnnoMessaggi.stampaOpzioniAutoreAnnoPagina(autoreTitolo,
                        annoPubblicazione,paginaCorrente);
                    titoloSelezionatoInt=SceltaMenuMessaggi.inserimentoSceltaOpzioniPagina(paginaCorrente,
                        autoreTitolo.size()/RicercaLibro.MAX_RISULTATI_PAGINA+1,
                        autoreTitolo.size());

                    if(titoloSelezionatoInt==-1||titoloSelezionatoInt==-2) {
                        controllo=false;
                    }

                    if(titoloSelezionatoInt==-1) {
                        paginaCorrente=paginaCorrente+1;
                    }

                    if(titoloSelezionatoInt==-2) {
                        paginaCorrente=paginaCorrente-1;
                    }

                    NuovaSchermata.nuovaSchermata();

                } while (!controllo);
            }

            else {
                RicercaLibroAutoreAnnoMessaggi.stampaOpzioniAutoreAnno(autoreTitolo,annoPubblicazione);
                titoloSelezionatoInt=SceltaMenuMessaggi.inserimentoSceltaMenu(autoreTitolo.size());
                NuovaSchermata.nuovaSchermata();
            }

            titoloSelezionato=autoreTitolo.get(titoloSelezionatoInt-1).getTitolo();

            try {
                LibroDao libroDao=new LibroDaoImpl();
                libroSelezionato=libroDao.getIdByTitolo(titoloSelezionato);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                LibroDao libroDao=new LibroDaoImpl();
                libro=libroDao.getById(libroSelezionato);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        else {
            RicercaLibroAutoreAnnoMessaggi.valoriNonTrovati();
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

    /**
     * Restituisce il libro selezionato
     * dell'utente correlato alla ricerca.
     *
     * @return il libro
     */

    public Libro getLibro() {
        return libro;
    }
}
