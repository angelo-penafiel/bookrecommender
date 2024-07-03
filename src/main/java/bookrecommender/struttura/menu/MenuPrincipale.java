package bookrecommender.struttura.menu;

import bookrecommender.elaborazione.dao.ConsigliatoDao;
import bookrecommender.elaborazione.dao.daoimpl.ConsigliatoDaoImpl;
import bookrecommender.elaborazione.entities.Consigliato;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.MenuPrincipaleMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.struttura.consigliati.SelezioneConsigliati;
import bookrecommender.struttura.libreria.MenuLibreria;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import bookrecommender.struttura.visualizzazionelibro.VisualizzazioneLibro;
import java.io.IOException;

/**
 * Classe che ha la funzione di gestire la
 * sezione del menu principale.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class MenuPrincipale {

    //CAMPO

    private int scelta;

    //COSTRUTTORE

    /**
     * Restituisce l'oggetto di tipo MenuPrincipale
     * e all'interno viene effettuata la stampa
     * del menu, l'inserimento della scelta del
     * menu iniziale e l'inidirizzamento del flusso
     * del programma in base alla scelta effettuata.
     */

    public MenuPrincipale() {

        boolean controllo;

        do {

            controllo = true;

            NuovaSchermata.nuovaSchermata();
            MenuPrincipaleMessaggi.menuSenzaRegistrazione();

            scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

            if(scelta==1) {
                var ricercaLibro=new RicercaLibro(0);

                if(ricercaLibro.getScelta()==4) {
                    controllo=false;
                }
            }

            if(scelta==2) {

            }

            if(scelta==3) {

            }

        } while(!controllo);
    }

    public MenuPrincipale(String userId) {

        boolean controllo;

        do {

            controllo = true;

            NuovaSchermata.nuovaSchermata();

            Consigliato consigliato;

            ConsigliatoDao consigliatoDao =new ConsigliatoDaoImpl();

            try {
                consigliato= consigliatoDao.getByUserId(userId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(consigliato==null||consigliato.getLibriConsigliati()[0].equals("-1")) {

                MenuPrincipaleMessaggi.menuRegistrazioneSenzaConsigliati();
                scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);

                if(scelta==1) {
                    var ricercaLibro=new RicercaLibro(1);

                    if(ricercaLibro.getScelta()==4) {
                        controllo=false;
                    }

                    if(ricercaLibro.getScelta()==5) {
                        scelta=4;
                    }
                }

                else if(scelta==2) {
                    var menuLibreria=new MenuLibreria(userId);

                    if(menuLibreria.getScelta()==4) {
                        controllo=false;
                    }

                    if(menuLibreria.getScelta()==5) {
                        scelta=4;
                    }

                }

                else {
                    scelta++;
                }

            }

            else {

                MenuPrincipaleMessaggi.menuRegistrazioneConsigliati();
                scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(5);

                if(scelta==1) {
                    var ricercaLibro=new RicercaLibro(1);

                    if(ricercaLibro.getScelta()==4) {
                        controllo=false;
                    }

                    if(ricercaLibro.getScelta()==5) {
                        scelta=4;
                    }
                }

                if(scelta==2) {
                    var menuLibreria=new MenuLibreria(userId);

                    if(menuLibreria.getScelta()==4) {
                        controllo=false;
                    }

                    if(menuLibreria.getScelta()==5) {
                        scelta=4;
                    }

                }

                if(scelta==3) {
                    var selezioneConsigliati=new SelezioneConsigliati(userId);
                    var visualizzazioneLibro=new VisualizzazioneLibro(2, selezioneConsigliati.getLibro());

                    if(visualizzazioneLibro.getScelta()==1) {
                        controllo=false;
                    }

                    else {
                        scelta=visualizzazioneLibro.getScelta()+2;
                    }
                }
            }

        } while(!controllo);
    }

    //METODI

    /**
     * Restituisce la scelta effettuata
     * del menu principale dall'utente.
     *
     * @return scelta inserita dall'utente
     */

    public int getScelta() {
        return scelta;
    }

}
