package bookrecommender.interfaccia;

/**
 * Classe che ha la funzione di stampare gli
 * invio/new line per pulire la schermata.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class NuovaSchermata {


    //CAMPO

    /**
     * Costante che indica il numero di invio/
     * new line da effettuare per pulire la
     * schermata.
     */

    private static final int CLS = 20;


    //COSTRUTTORE

    private NuovaSchermata() {

    }


    //METODO

    /**
     * Pulisce la schermata.
     */

    public static void nuovaSchermata() {
        for(var i=0;i<CLS;i++) {
            System.out.println("\n");
        }
    }
}
