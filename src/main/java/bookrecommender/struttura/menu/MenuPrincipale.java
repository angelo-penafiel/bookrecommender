
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.struttura.menu;

import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.MenuPrincipaleMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;

import java.io.IOException;

public class MenuPrincipale {

    private int scelta;

    public MenuPrincipale(int continuaSenzaRegistrazione) {

        var controllo = true;

        do {

            NuovaSchermata.nuovaSchermata();
            MenuPrincipaleMessaggi.menuSenzaRegistrazione();

            scelta = SceltaMenu.sceltaMenu(4);

            if(scelta==1) {
                try {
                    controllo= cercaLibri();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(scelta==2) {

            }

            if(scelta==3) {

            }

        } while(!controllo);
    }

    private boolean cercaLibri() throws IOException {

        var controllo=true;

        var ricercaLibro=new RicercaLibro(0);

        return controllo;
    }

    public int getScelta() {
        return scelta;
    }

}
