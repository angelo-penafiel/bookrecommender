package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.InserimentoConsigliatiMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InserimentoConsigliati  {

  public InserimentoConsigliati(String UserID, Libro l)throws IOException {



    BufferedWriter writer = new BufferedWriter(new FileWriter("data/ConsigliLibri.dati.csv", true));
    writer.write("");
    writer.close();

   /* var ricercaLibro=new RicercaLibro(2);
    Libro libro = ricercaLibro.getLibro();
    int scelta = ricercaLibro.getScelta();
*/
    NuovaSchermata.nuovaSchermata();



    InserimentoConsigliatiMessaggi.consigliatoAggiunto();

  }

}
