/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.struttura.menu;

import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import java.util.Scanner;

public final class SceltaMenu {

    private SceltaMenu() {

    }

    public static int sceltaMenu(int scelte) {

        var scelta = 0;
        String voce;
        boolean controllo;
        var in=new Scanner(System.in);

        do {
            controllo=true;

            SceltaMenuMessaggi.inserimento();
            voce=in.nextLine();

            for(var i=0;i<voce.length();i++) {
                if(!Character.isDigit(voce.charAt(i))) {
                    controllo=false;
                }
            }

            if(voce.isEmpty()) {
                controllo=false;
            }

            if(controllo) {
                scelta=Integer.parseInt(voce);
                if(scelta<1||scelta>scelte) {
                    controllo=false;
                    SceltaMenuMessaggi.erroreScelte(scelte);
                }
            }

            else {
                SceltaMenuMessaggi.erroreStringa();
            }

        } while(!controllo);

        return scelta;
    }
}
