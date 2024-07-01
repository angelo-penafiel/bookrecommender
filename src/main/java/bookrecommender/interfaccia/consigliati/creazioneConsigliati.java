package bookrecommender.interfaccia.consigliati;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class creazioneConsigliati {



    public creazioneConsigliati(String userID)  {

        try {


            BufferedWriter writer = new BufferedWriter(new FileWriter("data/ConsigliLibri.dati.csv", true));
            writer.write(userID + ",-1,-1,-1");
            writer.close();

        }catch (IOException e)
        {}
    }

}
