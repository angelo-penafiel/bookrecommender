package bookrecommender.struttura.menu;

import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.MenuInizialeMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;

/**
 * Classe che ha la funzione di gestire la
 * sezione del menu iniziale.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class MenuIniziale {

    //CAMPO

    /**
     * Campo che inidica la scelta del menu
     * che effettua l'utente.
     */

    private final int scelta;

    //COSTRUTTORE

    /**
     * Restituisce l'oggetto di tipo MenuIniziale
     * e all'interno viene effettuata la stampa
     * del menu e l'inserimento della scelta del
     * menu iniziale.
     */

    public MenuIniziale() {

        NuovaSchermata.nuovaSchermata();
        MenuInizialeMessaggi.menu();
        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(3);
    }

    //METODO

    /**
     * Restituisce la scelta effettuata
     * del menu iniziale dall'utente.
     *
     * @return la scelta inserita dall'utente
     */

    public int getScelta() {
        return scelta;
    }
}
