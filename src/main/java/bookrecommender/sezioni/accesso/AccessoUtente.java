
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.accesso;

import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.exceptions.UtenteException;
import bookrecommender.elaborazione.services.UtenteService;
import bookrecommender.interfaccia.accesso.AccessoUtenteMessaggi;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.sezioni.menu.SceltaMenu;

import java.util.Scanner;

public class AccessoUtente {

    private String username;

    private String password;

    private int scelta;

    private boolean controllo;

    private final Scanner in=new Scanner(System.in);

    private Utente utente;

    public AccessoUtente() {

        do {

            controllo=true;

            inserimentoDati();

            autenticazione();

            if(!controllo) {

                NuovaSchermata.nuovaSchermata();
                AccessoUtenteMessaggi.erroreUtenteNonTrovato();
                AccessoUtenteMessaggi.menuSelezione();
                scelta = SceltaMenu.sceltaMenu(2);

                if(scelta==1) {
                    //Torna a reinserire i dati
                    controllo=false;
                }

                if(scelta==2) {
                    //Torna al menu iniziale
                    NuovaSchermata.nuovaSchermata();
                    controllo=true;
                }
            }

            else {
                scelta=0;
            }

        } while (!controllo);
    }

    public int getScelta() {
        return scelta;
    }

    public Utente getUtente() {
        return utente;
    }

    private void inserimentoDati() {

        inserimentoUsername();
        inserimentoPassword();
    }

    private void inserimentoUsername() {

        NuovaSchermata.nuovaSchermata();
        AccessoUtenteMessaggi.titolo();

        AccessoUtenteMessaggi.inserimentoUsername();
        username=in.nextLine();

        if(username.length()< Utente.MIN_USERNAME
                ||username.length()> Utente.MAX_USERNAME) {

            AccessoUtenteMessaggi.erroreLunghezzaUsername();
            throw new UtenteException(" Errore! L'username deve essere tra " + Utente.MIN_USERNAME
                    +" e "+ Utente.MAX_USERNAME+" caratteri");
        }
    }

    private void inserimentoPassword() {

        AccessoUtenteMessaggi.inserimentoPassword();
        password=in.nextLine();

        if(password.length()< Utente.MIN_PASSWORD
                ||password.length()> Utente.MAX_PASSWORD) {

            AccessoUtenteMessaggi.erroreLunghezzaPassword();
            throw new UtenteException(" Errore! La password deve essere tra" +
                    " "+ Utente.MIN_PASSWORD+" e "+ Utente.MAX_PASSWORD+" caratteri");
        }
    }

    private void autenticazione() {

        var utenteService=new UtenteService();
        utente = utenteService.getByUsernamePassword(username,password);

        if(utente==null) {
            controllo=false;
        }

    }
}
