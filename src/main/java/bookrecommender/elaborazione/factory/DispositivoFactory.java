
/*
 * Autore: Penafiel Angelo.
 * Progetto: casa domotica
 */

package bookrecommender.elaborazione.factory;

import bookrecommender.elaborazione.entities.Lavatrice;
import bookrecommender.elaborazione.entities.Luce;

import java.time.LocalDateTime;
import java.util.List;

public final class DispositivoFactory {

    private DispositivoFactory() {

    }

    public static Dispositivo getDispositivo (DispositivoEnum dispositivoEnum, List<Object> parametri) {

        return switch (dispositivoEnum){

            case LUCE_CON_ID -> istanziaLuceId(parametri);

            case LUCE_SENZA_ID -> istanziaLuceSenzaId(parametri);

            case LAVATRICE_CON_ID -> istanziaLavatriceId(parametri);

            case LAVATRICE_SENZA_ID -> istanziaLavatriceSenzaId(parametri);

        };
    }

    private static Dispositivo istanziaLuceId(List<Object> parametri) {

        Integer id;
        String nome;
        Boolean acceso;

        id=(Integer) parametri.get(0);
        nome=(String) parametri.get(1);
        acceso=(Boolean) parametri.get(2);

        return new Luce(id,nome,acceso);
    }

    private static Dispositivo istanziaLuceSenzaId(List<Object> parametri) {

        String nome;
        Boolean acceso;

        nome=(String) parametri.get(0);
        acceso=(Boolean) parametri.get(1);

        return new Luce(nome,acceso);
    }

    private static Dispositivo istanziaLavatriceId(List<Object> parametri) {

        Integer id;
        String nome;
        Boolean acceso;
        Integer temperatura;
        Integer tempo;
        LocalDateTime inizio;
        LocalDateTime fine;

        id=(Integer) parametri.get(0);
        nome=(String) parametri.get(1);
        acceso=(Boolean) parametri.get(2);
        temperatura=(Integer) parametri.get(3);
        tempo=(Integer) parametri.get(4);
        inizio=(LocalDateTime) parametri.get(5);
        fine=(LocalDateTime) parametri.get(6);

        return new Lavatrice(id,nome,acceso,temperatura,tempo,inizio,fine);
    }

    private static Dispositivo istanziaLavatriceSenzaId(List<Object> parametri) {

        String nome;
        Boolean acceso;
        Integer temperatura;
        Integer tempo;
        LocalDateTime inizio;
        LocalDateTime fine;

        nome=(String) parametri.get(0);
        acceso=(Boolean) parametri.get(1);
        temperatura=(Integer) parametri.get(2);
        tempo=(Integer) parametri.get(3);
        inizio=(LocalDateTime) parametri.get(4);
        fine=(LocalDateTime) parametri.get(5);

        return new Lavatrice(nome,acceso,temperatura,tempo,inizio,fine);
    }
}
