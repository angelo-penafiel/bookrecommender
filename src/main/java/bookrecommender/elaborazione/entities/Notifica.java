
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.entities;

import java.time.LocalDateTime;

public class Notifica {

    private Integer id;

    private String testo;

    private Integer utenteId;

    private final LocalDateTime localDateTime;

    public Notifica(Integer id, String testo, Integer utenteId, LocalDateTime localDateTime) {
        this.id=id;
        this.testo =testo;
        this.utenteId=utenteId;
        this.localDateTime=localDateTime;
    }

    public Notifica(String testo, Integer utenteId, LocalDateTime localDateTime) {
        this.testo =testo;
        this.utenteId=utenteId;
        this.localDateTime=localDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Integer getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(Integer utenteId) {
        this.utenteId = utenteId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

}
