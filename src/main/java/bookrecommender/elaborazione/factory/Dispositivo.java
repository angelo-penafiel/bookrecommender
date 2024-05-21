
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.factory;

import bookrecommender.elaborazione.observer.Observer;

public interface Dispositivo {

    Integer getId();

    void setId(Integer id);

    String getNome();

    void setNome(String nome);

    Boolean getAcceso();

    void setAcceso (Boolean acceso);

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void inviaNotifica(int notifica);
}
