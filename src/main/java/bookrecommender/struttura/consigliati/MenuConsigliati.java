package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.utils.singleton.ConsigliatiHashMap;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.MenuConsigliatiMessaggi;
import bookrecommender.interfaccia.consigliati.creazioneConsigliati;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;

import java.io.IOException;
import java.util.Arrays;

public class MenuConsigliati {

    private int scelta;

    public MenuConsigliati(String userID, Libro l) {

        boolean controllo=true;
        boolean sicurezza=true;
        ConsigliatiHashMap cons = ConsigliatiHashMap.getInstance();
        String control="-1";
        String[] utenti = cons.getValues(userID);






    do {
        if(utenti==null)
        {
            System.out.println("non esiste il tuo utente");
            sicurezza=false;
            creazioneConsigliati io=new creazioneConsigliati(userID);


             cons = ConsigliatiHashMap.getInstance2();

             utenti = cons.getValues(userID);
             controllo = false;
        }



        if(control.equals(utenti[3])&&sicurezza) {
            //  Arrays.toString(utenti)
        try {
            controllo = true;

            //verifica se sono presenti libri consigliati

            NuovaSchermata.nuovaSchermata();

            if (true) {

                MenuConsigliatiMessaggi.menuConsigliati();
                scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(7);

                if (scelta == 1) {

                    var inserimentoConsigliati = new InserimentoConsigliati(userID, l);

                    controllo = false;

                } else if (scelta == 2) {

                   // var selezioneConsigliati = new SelezioneConsigliati();

                    var inserimentoConsigliati = new InserimentoConsigliati(userID, l);

                    controllo = false;

                }
            }
        } catch (IOException e) {
            System.out.println(" ");
        }

    }
        else if(sicurezza)
        {
            System.out.println("Hai già consigliato 3 libri non puoi consigliarne altri");
        }


    } while (!controllo);









    }
    public int getScelta () {
        return scelta;
    }







}

