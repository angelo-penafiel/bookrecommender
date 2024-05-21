
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.observer;

public interface Observer {

    void updateLuce(String nome, Boolean acceso);

    void updateLavatriceStatoAccensione(String nome, Boolean acceso);

    void updateLavatriceStatoLavaggio(String nome, Integer temperatura, Integer tempo);
}
