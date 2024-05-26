
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.struttura.menu;

import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.MenuPrincipaleMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;

public class MenuPrincipale {

    private int scelta;

    public MenuPrincipale(int continuaSenzaRegistrazione) {

        var controllo = true;

        do {

            NuovaSchermata.nuovaSchermata();
            MenuPrincipaleMessaggi.menuSenzaRegistrazione();

            scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

            if(scelta==1) {
                controllo = cercaLibri();
            }

            if(scelta==2) {

            }

            if(scelta==3) {

            }

        } while(!controllo);
    }

    private boolean cercaLibri() {

        var controllo=true;

        var ricercaLibro=new RicercaLibro(0);

        return controllo;
    }

    public int getScelta() {
        return scelta;
    }

}
