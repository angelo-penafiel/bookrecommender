package bookrecommender.elaborazione.entities.user;

import java.io.*;

import bookrecommender.interfaccia.register.RegistrazioneMessaggi;
import bookrecommender.interfaccia.register.LoginMessaggi;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class User {

    public static void register(){
        try {
            String[] user = RegistrazioneMessaggi.in();
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/Database/UtentiRegistrati.csv", true));

            if (user != null) {
                writer.write(user[0] + "," + user[1] + "," + user[2] + "," + user[3] + "," + user[4] + "," + user[5] + "\n");
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void login(){
        try {
            FileReader reader = new FileReader("data/Database/UtentiRegistrati.csv");
            String[] LoginData = LoginMessaggi.in();
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader("Nome", "Cognome", "UserID", "Taxcode", "Mail", "Password")
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser parser = new CSVParser(reader, csvFormat);

            for (CSVRecord record: parser ) {
                if (record.get("UserID").equals(LoginData[0]) && record.get("Password").equals(LoginData[1])) {
                    System.out.println("SUCCESS!!!");
                    break;
                } else System.out.println("Nope");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
