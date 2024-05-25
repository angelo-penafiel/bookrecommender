package bookrecommender.elaborazione.entities.user;

import java.io.*;

import bookrecommender.interfaccia.register.RegistrazioneMessaggi;
import bookrecommender.interfaccia.register.LoginMessaggi;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * This class implements some useful methods for handling the user and its data
 * @author Leonardo Basso
 * @see bookrecommender.interfaccia.register.RegistrazioneMessaggi
 * @see bookrecommender.elaborazione.entities.user.PasswordManager
 */
public class User {
    /**
     * This method registers a user into a {@code .csv} file
     * The parameters saved are: {@code Nome}, {@code Cognome}, {@code UserID}, {@code Taxcode}, {@code Mail}, {@code Password}
     */
    public static void register() {
        try {
            String[] user = RegistrazioneMessaggi.in();
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/Database/UtentiRegistrati.csv", true));

            if (user != null) {
                writer.write(user[0] + "," + user[1] + "," + user[2] + "," + user[3] + "," + user[4] + "," + user[5] + "\n");
                writer.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method logs a user into the app using <a href="https://commons.apache.org/proper/commons-csv/">common-csv</a> by Apache
     */
    public static void login() {
        try {
            FileReader reader = new FileReader("data/Database/UtentiRegistrati.csv");
            String[] LoginData = LoginMessaggi.in();
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader("Nome", "Cognome", "UserID", "Taxcode", "Mail", "Password")
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser parser = new CSVParser(reader, csvFormat);

            for (CSVRecord record : parser) {
                if (record.get("UserID").equals(LoginData[0]) && PasswordManager.compare(LoginData[1], record.get("Password"))) {
                    System.out.println("SUCCESS!");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
