
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.struttura;

import bookrecommender.struttura.menu.MenuIniziale;
import bookrecommender.struttura.menu.MenuPrincipale;

public class BookRecommender {

    public static void main(String[] args) {

        boolean controllo;

        do {
            controllo=true;

            controllo=menuIniziale(controllo);

        } while (!controllo);

    }

    private static boolean menuIniziale(boolean controllo) {

        var menuIniziale=new MenuIniziale();

        if(menuIniziale.getScelta()==1) {

            //accesso utente
        }

        if(menuIniziale.getScelta()==2) {

            //registrazione

        }

        if(menuIniziale.getScelta()==3) {

            var menuPrincipale=new MenuPrincipale(0);

        }

        return controllo;
    }

}
