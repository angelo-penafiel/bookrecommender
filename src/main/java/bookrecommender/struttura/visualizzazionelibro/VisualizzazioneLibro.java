package bookrecommender.struttura.visualizzazionelibro;

import bookrecommender.elaborazione.dao.ConsigliatoDao;
import bookrecommender.elaborazione.dao.ValutazioneDao;
import bookrecommender.elaborazione.dao.daoimpl.ConsigliatoDaoImpl;
import bookrecommender.elaborazione.dao.daoimpl.ValutazioneDaoImpl;
import bookrecommender.elaborazione.entities.Consigliato;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.Valutazione;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.visualizzazioneLibro.VisualizzazioneLIbroMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import java.io.IOException;
import java.util.List;

public class VisualizzazioneLibro {

  private Libro libro;

  private int scelta;

  public VisualizzazioneLibro(int menuProvenienza, Libro libro) {

    this.libro=libro;

    List<Consigliato> consigliati;

    ConsigliatoDao consigliatoDao=new ConsigliatoDaoImpl();
    try {
      consigliati=consigliatoDao.getByLibroId(libro.getId().toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    List<Valutazione> valutazioni;

    ValutazioneDao valutazioneDao=new ValutazioneDaoImpl();
    try {
      valutazioni=valutazioneDao.getByLibroId(libro.getId().toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Valutazione media;

    if(valutazioni.size()==0) {
      media=new Valutazione((double)0,(double)0,(double)0,(double)0,(double)0,(double)0);
    }

    else  {

      Valutazione somma=new Valutazione((double)0,(double)0,(double)0,(double)0,(double)0,(double)0);

      for(Valutazione valutazione:valutazioni) {
        somma.setStile(somma.getStile()+valutazione.getStile());
        somma.setContenuto(somma.getContenuto()+valutazione.getContenuto());
        somma.setGradevolezza(somma.getGradevolezza()+valutazione.getGradevolezza());
        somma.setOriginalita(somma.getOriginalita()+valutazione.getOriginalita());
        somma.setEdizione(somma.getEdizione()+valutazione.getEdizione());
        somma.setFinale(somma.getFinale()+valutazione.getFinale());
      }

      int size=valutazioni.size();

      media=new Valutazione(somma.getStile()/size,
          somma.getContenuto()/size, somma.getGradevolezza()/size,
          somma.getOriginalita()/size,somma.getEdizione()/size,
          somma.getFinale()/size);
    }

    NuovaSchermata.nuovaSchermata();
    VisualizzazioneLIbroMessaggi.intestazione();
    VisualizzazioneLIbroMessaggi.stampaDatiLibro(libro,consigliati.size(),media);

    if(menuProvenienza==0) {
      VisualizzazioneLIbroMessaggi.menuSceltaSenzaRegistrazione();
      scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(3);
    }

    if(menuProvenienza==1) {
      VisualizzazioneLIbroMessaggi.menuSceltaUtenteRegistrato();
      scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);
    }

    if(menuProvenienza==2) {
      VisualizzazioneLIbroMessaggi.menuSceltaUtenteConsigliati();
      scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(3);
    }

  }

  /**
   * Restituisce la scelta effettuata
   * del menu di selezione.
   */

  public int getScelta() {
    return scelta;
  }

}
