package bookrecommender.struttura.menu;

import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.MenuPrincipaleMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;

/**
 * Classe che ha la funzione di gestire la
 * sezione del menu principale.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class MenuPrincipale {

    //CAMPO

    private int scelta;

    //COSTRUTTORE

    /**
     * Restituisce l'oggetto di tipo MenuPrincipale
     * e all'interno viene effettuata la stampa
     * del menu, l'inserimento della scelta del
     * menu iniziale e l'inidirizzamento del flusso
     * del programma in base alla scelta effettuata.
     */

    public MenuPrincipale() {

        boolean controllo;

        do {

            controllo = true;

            NuovaSchermata.nuovaSchermata();
            MenuPrincipaleMessaggi.menuSenzaRegistrazione();

            scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

            if(scelta==1) {
                var ricercaLibro=new RicercaLibro(0);

                if(ricercaLibro.getScelta()==4) {
                    controllo=false;
                }
            }

            if(scelta==2) {

            }

            if(scelta==3) {

            }

        } while(!controllo);
    }

    //METODI

    /**
     * Restituisce la scelta effettuata
     * del menu principale dall'utente.
     *
     * @return scelta inserita dall'utente
     */

    public int getScelta() {
        return scelta;
    }

}
