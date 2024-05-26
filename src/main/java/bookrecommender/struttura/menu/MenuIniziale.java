
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.struttura.menu;

import bookrecommender.interfaccia.menu.MenuInizialeMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;

public class MenuIniziale {

    private final int scelta;

    public MenuIniziale() {

        MenuInizialeMessaggi.menu();
        scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(3);
    }

    public int getScelta() {
        return scelta;
    }
}
