package bookrecommender.struttura.menu;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.MenuAzioniLibroMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.consigliati.MenuConsigliati;
import bookrecommender.struttura.valutazione.MenuValutazione;

/**
 * Classe che ha la funzione di gestire la
 * sezione del menu azioni libro.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class MenuAzioniLibro {


    //CAMPO

    /**
     * Campo che indica il numero di scelta
     * effettuata del menu azioni libro.
     */

    private int scelta;


    //COSTRUTTORE

    /**
     * Restituisce l'oggetto di tipo MenuAzioniLibro
     * e all'interno viene effettuata la stampa
     * del menu e l'inserimento della scelta del
     * menu azioni libro. Successivamente viene gestito
     * il flusso del programma in base alla scelta
     * effettuata.
     *
     * @param userId indica lo userId dell'utente
     *               loggato
     *
     * @param userId indica il libro corrente
     */

    public MenuAzioniLibro(String userId, Libro l) {

        boolean controllo;
        System.out.println("**************************************************");
        System.out.println("**************************************************"+l.getTitolo());
        do {

            controllo = true;

            NuovaSchermata.nuovaSchermata();

            MenuAzioniLibroMessaggi.menu();
            scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

            if (scelta == 1) {

                var menuValutazione = new MenuValutazione(userId, l);

                if (menuValutazione.getScelta() == 2) {
                    controllo = false;
                }

                if (menuValutazione.getScelta() > 2) {
                    scelta = menuValutazione.getScelta();
                }

            }

            if (scelta == 2) {

                var menuConsigliati = new MenuConsigliati(userId, l);

                if(menuConsigliati.getScelta()==3) {
                    controllo=false;
                }

                if(menuConsigliati.getScelta()>3) {
                    scelta=menuConsigliati.getScelta()-1;
                }

            }

        } while (!controllo);

    }


    //METODO

    /**
     * Restituisce la scelta effettuata
     * del menu azioni libro.
     *
     * @return la scelta
     */

    public int getScelta() {
        return scelta;
    }
}
