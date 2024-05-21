
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.menu;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.MenuPrincipaleMessaggi;
import bookrecommender.sezioni.luci.MenuLuci;
import bookrecommender.sezioni.notifiche.GestioneNotifiche;
import bookrecommender.sezioni.lavatrici.MenuLavatrici;
import bookrecommender.sezioni.ricercalibro.RicercaLibro;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuPrincipale {

    private int scelta;

    private Utente utente;

    public MenuPrincipale(Utente utente) {

        this.utente=utente;

        var controllo = true;

        do {

            NuovaSchermata.nuovaSchermata();
            MenuPrincipaleMessaggi.menu(utente.getUsername());

            scelta = SceltaMenu.sceltaMenu(5);

            if(scelta==1) {
                controllo=luci();
            }

            if(scelta==2) {
                controllo=lavatrici();
            }

            if(scelta==3) {
                controllo=notifiche();
            }

            if(scelta==4) {
                //log out
                NuovaSchermata.nuovaSchermata();
            }

        } while(!controllo);
    }

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
                controllo=lavatrici();
            }

            if(scelta==3) {

            }

        } while(!controllo);
    }

    private boolean luci() {

        var controllo=true;

        var menuLuci = new MenuLuci(utente);

        if(menuLuci.getScelta()==4) {
            controllo=false;
        }

        if(menuLuci.getScelta()==5) {
            //log out
            scelta=4;
        }

        if(menuLuci.getScelta()==6) {
            //esci dal programma
            scelta=5;
        }

        return controllo;
    }

    private boolean lavatrici() {

        var controllo=true;

        var menuLavatrici=new MenuLavatrici(utente);

        if(menuLavatrici.getScelta()==4) {
            controllo=false;
        }

        if(menuLavatrici.getScelta()==5) {
            //log out
            scelta=4;
        }

        if(menuLavatrici.getScelta()==6) {
            //esci dal programma
            scelta=5;
        }

        return controllo;
    }

    private boolean notifiche() {

        var controllo=true;

        var notifiche=new GestioneNotifiche(utente);      //notifiche

        if(notifiche.getScelta()==1) {
            controllo=false;
        }

        if(notifiche.getScelta()==2) {
            scelta=4;
        }

        if(notifiche.getScelta()==3) {
            scelta=5;
        }

        return controllo;
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
