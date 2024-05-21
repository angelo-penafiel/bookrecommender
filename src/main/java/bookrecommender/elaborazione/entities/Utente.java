
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.entities;

import bookrecommender.elaborazione.observer.Observer;
import bookrecommender.elaborazione.dao.NotificaDao;
import bookrecommender.elaborazione.dao.daoimpl.NotificaDaoImpl;

import java.time.LocalDateTime;

public class Utente implements Observer {

    public static final int MAX_USERNAME = 25;

    public static final int MIN_USERNAME = 5;

    public static final int MAX_PASSWORD = 25;

    public static final int MIN_PASSWORD = 7;

    private Integer id;

    private String username;

    private String password;

    public Utente(String username, String password) {

        this.username=username;
        this.password=password;
    }

    public Utente(Integer id, String username, String password) {

        this.id=id;
        this.username=username;
        this.password=password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void updateLuce(String nome, Boolean acceso) {

        String testo;

        if(Boolean.TRUE.equals(acceso)) {
            testo="accesa.";
        }
        else {
            testo="spenta.";
        }

        var notifica=new Notifica("La luce di "+nome+" è stata "+testo,id,LocalDateTime.now());
        NotificaDao<Notifica> notificaDao =new NotificaDaoImpl();
        notificaDao.create(notifica,id);

    }

    @Override
    public void updateLavatriceStatoAccensione(String nome, Boolean acceso) {

        String testo="La lavatrice di "+nome;

        if(Boolean.TRUE.equals(acceso)) {
            testo+=" è stata accesa.";
        }

        else {
            testo+=" è stata spenta.";
        }

        var notifica=new Notifica(testo,id,LocalDateTime.now());
        NotificaDao<Notifica> notificaDao =new NotificaDaoImpl();
        notificaDao.create(notifica,id);

    }

    @Override
    public void updateLavatriceStatoLavaggio(String nome, Integer temperatura, Integer tempo) {

        String testo="La lavatrice di "+nome;

        if(temperatura==0&&tempo==0) {
            testo+=" non sta lavando.";
        }

        else {
            testo+=" sta lavando a "+temperatura+" °C per altri "+tempo+" minuti.";
        }

        var notifica=new Notifica(testo,id,LocalDateTime.now());
        NotificaDao<Notifica> notificaDao =new NotificaDaoImpl();
        notificaDao.create(notifica,id);
    }
}
