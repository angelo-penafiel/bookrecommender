package bookrecommender.interfaccia.register;

import java.util.Scanner;

public class LoginMessaggi {

    public static String[] in() {
        String[] Login = new String[2];
        Scanner in = new Scanner(System.in);

        System.out.print("Inserisci il tuo UserID: ");
        Login[0] = in.nextLine();

        System.out.print("Inserisci la tua password: ");
        Login[1] = in.nextLine();

        return Login;

    }
}
