package bookrecommender.elaborazione.dao;

import bookrecommender.elaborazione.entities.Valutazione;
import java.io.IOException;
import java.util.List;

public interface ValutazioneDao {

  List<Valutazione> getByLibroId(String libroId) throws IOException;

}
