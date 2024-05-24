
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.struttura.menu;

import bookrecommender.interfaccia.menu.MenuInizialeMessaggi;

public class MenuIniziale {

    private final int scelta;

    public MenuIniziale() {

        MenuInizialeMessaggi.menu();
        scelta = SceltaMenu.sceltaMenu(3);
    }

    public int getScelta() {
        return scelta;
    }
}
