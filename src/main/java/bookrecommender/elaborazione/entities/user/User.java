package bookrecommender.elaborazione.entities.user;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import bookrecommender.interfaccia.register.RegistrazioneMessaggi;

public class User {

    public static void main(String[] args) {
        register();
    }

    public static void register(){
        try {
            String[] user = RegistrazioneMessaggi.inserisci();
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/Database/UtentiRegistrati.csv", true));

            if (user != null) {
                writer.write(user[0] + "," + user[1] + "," + user[2] + "," + user[3] + "," + user[4] + "," + user[5] + "\n");
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
