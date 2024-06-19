package bookrecommender.elaborazione.entities.user;

import java.io.*;

import bookrecommender.interfaccia.register.RegistrazioneMessaggi;
import bookrecommender.interfaccia.register.LoginMessaggi;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Questa classe implementa alcuni metodi utili per la gestione dell'utente e dei suoi dati
 * @author Leonardo Basso
 * @see RegistrazioneMessaggi
 * @see PasswordManager
 */
public class User {
    /**
     * Questo metodo registra un'utente in un file {@code .csv}
     * I parametri salvati sono: {@code Nome}, {@code Cognome}, {@code UserID}, {@code Taxcode}, {@code Mail}, {@code Password}
     */
    public static String register() {
        try {
            String[] user = RegistrazioneMessaggi.in();
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/Database/UtentiRegistrati.csv", true));
            if (user != null) {
                writer.write(user[0] + "," + user[1] + "," + user[2] + "," + user[3] + "," + user[4] + "," + user[5] + "\n");
                writer.close();
                return user[2];
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo consente a un utente di accedere all'app usando:
     * <a href="https://commons.apache.org/proper/commons-csv/">common-csv</a> by Apache
     * @return String[] with the UserID and the Password
     */
    public static String login() {
        try {
            FileReader reader = new FileReader("data/Database/UtentiRegistrati.csv");
            String[] LoginData = LoginMessaggi.in();
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser parser = new CSVParser(reader, csvFormat);
            String out = " ";
            for (CSVRecord record : parser) {
                if (record.get("UserID").equals(LoginData[0]) && PasswordManager.compare(LoginData[1], record.get("Password"))) {
                    out = LoginData[0];
                    System.out.println("SUCCESS!");
                    return out;
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
