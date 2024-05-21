
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.entities;

import bookrecommender.elaborazione.factory.Dispositivo;
import bookrecommender.elaborazione.observer.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Lavatrice implements Dispositivo {

    public static final int MAX_NOME = 25;

    public static final int MIN_NOME = 3;

    public static final int MAX_TEMPERATURA = 95;

    public static final int MIN_TEMPERATURA = 30;

    public static final int MAX_TEMPO = 180;

    public static final int MIN_TEMPO = 30;

    private Integer id;

    private String nome;

    private Boolean acceso;

    private Integer temperatura;

    private Integer tempo;

    private LocalDateTime inizio;

    private LocalDateTime fine;

    private final List<Observer> subscribers = new ArrayList<>();

    public Lavatrice(String nome, Boolean acceso, Integer temperatura, Integer tempo,
                     LocalDateTime inizio, LocalDateTime fine) {
        this.nome =nome;
        this.acceso=acceso;
        this.temperatura=temperatura;
        this.tempo =tempo;
        this.inizio=inizio;
        this.fine=fine;
    }

    public Lavatrice(Integer id, String nome, Boolean acceso, Integer temperatura,
                     Integer tempo, LocalDateTime inizio, LocalDateTime fine) {

        this.id=id;
        this.nome =nome;
        this.acceso=acceso;
        this.temperatura=temperatura;
        this.tempo =tempo;
        this.inizio=inizio;
        this.fine=fine;

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

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public LocalDateTime getInizio() {
        return inizio;
    }

    public void setInizio(LocalDateTime inizio) {
        this.inizio = inizio;
    }

    public LocalDateTime getFine() {
        return fine;
    }

    public void setFine(LocalDateTime fine) {
        this.fine = fine;
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
    public void inviaNotifica(int notifica) {
        for (Observer observer : subscribers) {
            if(notifica==0) {
                observer.updateLavatriceStatoAccensione(nome,acceso);
            }
            if(notifica==1) {
                observer.updateLavatriceStatoLavaggio(nome,temperatura, tempo);
            }
        }
    }

}
