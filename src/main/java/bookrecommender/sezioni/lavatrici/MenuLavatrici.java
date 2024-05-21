
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.lavatrici;

import bookrecommender.elaborazione.entities.Lavatrice;
import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.services.LavatriceService;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.Headers;
import bookrecommender.interfaccia.lavatrici.MenuLavatriciMessaggi;
import bookrecommender.sezioni.menu.SceltaMenu;

import java.util.List;

public class MenuLavatrici {

    private int scelta;

    private Utente utente;

    private List<Lavatrice> lavatrici;

    public MenuLavatrici(Utente utente) {

        this.utente=utente;

        boolean controllo;

        do {

            var lavatriceService=new LavatriceService();
            lavatrici = lavatriceService.getAll(utente.getId());

            NuovaSchermata.nuovaSchermata();

            Headers.menuLavatrici(utente.getUsername());

            MenuLavatriciMessaggi.stampaLavatrici(lavatrici);

            if(lavatrici.isEmpty()) {
                controllo=lavatriciNonPresenti();
            }

            else {
                controllo=lavatriciPresenti();
            }

        } while(!controllo);
    }

    private boolean lavatriciNonPresenti() {

        var controllo = true;

        MenuLavatriciMessaggi.menuLavatriciNonPresenti();
        scelta = SceltaMenu.sceltaMenu(4);

        if(scelta==1) {

            var inserimento=new InserimentoLavatrici(utente);
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

    private boolean lavatriciPresenti() {

        var controllo = true;

        MenuLavatriciMessaggi.menuLavatriciPresenti();
        scelta=SceltaMenu.sceltaMenu(6);

        if(scelta==1) {

            var modificaStato=new ModificaStatoLavatrici(utente, lavatrici);

            if(modificaStato.getScelta()==3) {
                controllo =false;
            }
            if(modificaStato.getScelta()>=4&&modificaStato.getScelta()<=6) {
                scelta=modificaStato.getScelta();
            }

        }

        if(scelta==2) {

            var inserimento=new InserimentoLavatrici(utente);
            controllo =false;
        }

        if(scelta==3) {

            var eliminazione=new EliminazioneLavatrici(utente, lavatrici);
            controllo =false;
        }

        return controllo;
    }

    public int getScelta() {
        return scelta;
    }

}
