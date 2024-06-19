package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.InserimentoConsigliatiMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;

public class InserimentoConsigliati {

  public InserimentoConsigliati() {

    var ricercaLibro=new RicercaLibro(2);
    Libro libro = ricercaLibro.getLibro();
    int scelta = ricercaLibro.getScelta();

    NuovaSchermata.nuovaSchermata();
    InserimentoConsigliatiMessaggi.cosigliatoAggiunto();

  }

}
