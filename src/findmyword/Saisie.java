package findmyword;

import java.util.Scanner;

public class Saisie {
    private static final Scanner SAISIE = new Scanner(System.in);
    

    public String lireChaine() {
        return SAISIE.nextLine();
    }
}
