
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.luci;

import bookrecommender.elaborazione.entities.Luce;
import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.services.LuceService;
import bookrecommender.interfaccia.Headers;
import bookrecommender.interfaccia.luci.MenuLuciMessaggi;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.sezioni.menu.SceltaMenu;

import java.util.List;

public class MenuLuci {

    private int scelta;

    private Utente utente;

    private List<Luce> luci;

    public MenuLuci(Utente utente) {

        this.utente=utente;

        boolean controllo;

        do {

            var luceService=new LuceService();
            luci = luceService.getAll(utente.getId());

            NuovaSchermata.nuovaSchermata();
            Headers.menuLuci(utente.getUsername());
            MenuLuciMessaggi.stampaLuci(luci);

            if(luci.isEmpty()) {
                controllo=luciNonPresenti();
            }

            else {
                controllo=luciPresenti();
            }

        } while(!controllo);

    }

    private boolean luciPresenti() {

        var controllo = true;

        MenuLuciMessaggi.menuLuciPresenti();
        scelta=SceltaMenu.sceltaMenu(6);

        if(scelta==1) {

            var modificaStato=new ModificaStatoLuci(utente, luci);
            controllo =false;
        }

        if(scelta==2) {

            var inserimento=new InserimentoLuci(utente);
            controllo =false;
        }

        if(scelta==3) {

            var eliminazione=new EliminazioneLuci(utente, luci);
            controllo =false;
        }

        return controllo;
    }

    private boolean luciNonPresenti() {

        var controllo = true;

        MenuLuciMessaggi.menuLuciNonPresenti();
        scelta = SceltaMenu.sceltaMenu(4);

        if(scelta==1) {

            var inserimento=new InserimentoLuci(utente);
            controllo =false;
        }

        else if(scelta==2) {
            scelta=4;
        }

        else if(scelta==3) {
            scelta=5;
        }

        else {
            scelta=6;
        }

        return controllo;
    }

    public int getScelta() {
        return scelta;
    }

}
