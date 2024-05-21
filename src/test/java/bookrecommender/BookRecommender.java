
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender;

import bookrecommender.sezioni.accesso.AccessoUtente;
import bookrecommender.sezioni.menu.MenuIniziale;
import bookrecommender.sezioni.menu.MenuPrincipale;
import bookrecommender.sezioni.registrazione.RegistrazioneUtente;

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

            var accessoUtente = new AccessoUtente();

            if(accessoUtente.getScelta()==0) {

                var menuPrincipale=new MenuPrincipale(accessoUtente.getUtente());

                if(menuPrincipale.getScelta()==4) {
                    //Torna al menu iniziale
                    controllo=false;
                }
            }

            if(accessoUtente.getScelta()==2) {
                //Torna al menu iniziale
                controllo=false;
            }
        }

        if(menuIniziale.getScelta()==2) {

            var registrazioneUtente=new RegistrazioneUtente();

            var menuPrincipale=new MenuPrincipale(registrazioneUtente.getUtente());

            if(menuPrincipale.getScelta()==4) {
                //Torna al menu iniziale
                controllo=false;
            }

        }

        if(menuIniziale.getScelta()==3) {

            var menuPrincipale=new MenuPrincipale(0);

            if(menuPrincipale.getScelta()==4) {
                //Torna al menu iniziale
                controllo=false;
            }

        }

        return controllo;
    }

}
