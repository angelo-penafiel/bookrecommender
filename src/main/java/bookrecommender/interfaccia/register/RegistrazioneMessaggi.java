package bookrecommender.interfaccia.register;

import java.util.Scanner;

public class RegistrazioneMessaggi {

    public static String[] inserisci(){
        Scanner in = new Scanner(System.in);
        String[] user = new String[6];
        System.out.print("Inserisci il tuo nome: ");
        user[0] = in.nextLine();

        System.out.print("Inserisci il tuo conome: ");
        user[1] =in.nextLine();
        System.out.print("Inserisci il tuo UserID: ");
        user[2] =in.nextLine();

        System.out.print("Inserisci il tuo codice fiscale: ");
        user[3] =in.nextLine();

        System.out.print("Inserisci la tua mail: ");
        user[4] =in.nextLine();

        System.out.print("Inserisci la tua password: ");
        String pass = in.nextLine();

        System.out.print("Ripeti la tua password: ");
        if (in.nextLine().equals(pass)) {
            user[5] = pass;
            return user;
        } else {
            System.out.println("Le due password non corrispondono, vuoi riprovare (Y/N)? ");
            if (in.nextLine().equalsIgnoreCase("Y")) {
                inserisci();
            }
        }
        return null;
    }
}
