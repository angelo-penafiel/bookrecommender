
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.sezioni.registrazione;

import bookrecommender.elaborazione.entities.Utente;
import bookrecommender.elaborazione.exceptions.UtenteException;
import bookrecommender.elaborazione.services.UtenteService;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.registrazione.RegistrazioneUtenteMessaggi;

import java.util.Scanner;

public class RegistrazioneUtente {

    private String username;

    private String password;

    private final Scanner in=new Scanner(System.in);

    private Utente utente;

    public RegistrazioneUtente() {

        inserimentoDati();

        registrazione();

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
        RegistrazioneUtenteMessaggi.titolo();

        RegistrazioneUtenteMessaggi.inserimentoUsername();
        username=in.nextLine();

        if(username.length()< Utente.MIN_USERNAME
                ||username.length()> Utente.MAX_USERNAME) {

            RegistrazioneUtenteMessaggi.erroreLunghezzaUsername();
            throw new UtenteException(" Errore! L'username deve essere tra" +
                    " "+ Utente.MIN_USERNAME+" e "+ Utente.MAX_USERNAME+" caratteri");
        }

        else {

            var utenteService=new UtenteService();

            boolean exists = utenteService.existsByUsername(username);

            if(exists) {

                RegistrazioneUtenteMessaggi.erroreUsernameNonDisponibile();
                throw new UtenteException(" Errore! L'username è già stato inserito");
            }

        }

    }

    private void inserimentoPassword() {

        boolean controllo;

        do {
            controllo = true;

            RegistrazioneUtenteMessaggi.inserimentoPassword();
            password=in.nextLine();

            if(password.length()< Utente.MIN_PASSWORD
                    ||password.length()> Utente.MAX_PASSWORD) {

                controllo=false;
                RegistrazioneUtenteMessaggi.erroreLunghezzaPassword();
            }

        } while(!controllo);
    }

    private void registrazione() {

        var utenteService=new UtenteService();

        utente=new Utente(username,password);

        utente.setId(utenteService.add(utente));

    }
}
