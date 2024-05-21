
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.interfaccia;

public final class NuovaSchermata {

    private static final int CLS = 20;

    private NuovaSchermata() {

    }

    public static void nuovaSchermata() {
        for(var i=0;i<CLS;i++) {
            System.out.println("\n");
        }
    }
}
