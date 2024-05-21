
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.entities;

import bookrecommender.elaborazione.factory.Dispositivo;
import bookrecommender.elaborazione.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Luce implements Dispositivo {

    public static final int MAX_NOME = 25;

    public static final int MIN_NOME = 3;

    private Integer id;

    private String nome;

    private Boolean acceso;

    private final List<Observer> subscribers = new ArrayList<>();

    public Luce(Integer id, String nome, Boolean acceso) {
        this.id=id;
        this.nome =nome;
        this.acceso=acceso;
    }

    public Luce(String nome, Boolean acceso) {
        this.nome =nome;
        this.acceso=acceso;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Boolean getAcceso() {
        return acceso;
    }

    @Override
    public void setAcceso (Boolean acceso) {
        this.acceso = acceso;
    }

    @Override
    public void addObserver(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        subscribers.remove(observer);
    }

    @Override
    public void inviaNotifica(int notifica)  {

        for (Observer observer : subscribers) {
            if(notifica==0) {
                observer.updateLuce(nome,acceso);
            }
        }
    }

}
