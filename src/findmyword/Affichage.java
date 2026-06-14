package findmyword;

import java.util.ArrayList;

public class Affichage {

    private static final String RESET = "\u001B[0m";
    private static final String GRAS = "\u001B[1m";
    private static final String FOND_VERT = "\u001B[42m";
    private static final String FOND_JAUNE = "\u001B[43m";
    private static final String TEXTE_NOIR = "\u001B[30m";


    public Affichage (){
        
    }


    public void afficherDebutPartie (Joueur joueur){
        System.out.println("==============================\n        FindMyWord !!!\n==============================");
        System.out.println("Bienvenue dans le jeu FindMyWord !!!\n");
        System.out.println("Bonjour " + joueur.pseudoSaisie() + " !\n");
        System.out.println("""
                           Les r\u00e8gles sont simples : 
                            - Vous devez deviner le mot en 6 tentatives maximum.
                            - Chaque tentative doit \u00eatre un mot de 5 lettres.
                            - Les lettres doivent \u00eatre uniques
                            - Apr\u00e8s chaque tentative, vous recevrez des indices sur la position des lettres.
                           Bonne chance !!!
                           """);
    }


    public void afficherGrilleVide (int lignes){

        for (int i = 1; i<lignes; i++){
            String grilleVide = "";

            for (int j = 0; j< Word.NB_LETTRES_MAX; j++){
                grilleVide += "[   ] ";
            }

            grilleVide += "    ----->     ";

            for (int j = 0; j< Word.NB_LETTRES_MAX; j++){
                grilleVide += "*******    ";
            }

            System.out.println(grilleVide);
        }

        System.out.println("\n\n--------------------\n");
    }


    public void afficherClavier(String lettres, String lettresVertes, String lettresJaunes){
        System.out.println("CLAVIER\n");

        for (int i = 0; i < lettres.length(); i++){

            if (i == 0){
                System.out.print("   ");
            }

            if (i == 10){
                System.out.print("\n\n ");
            }

            if (i == 20){
                System.out.print("\n\n   ");
            }

            afficherLettreClavier(lettres.charAt(i), lettresVertes, lettresJaunes);
        }

        System.out.println("\n\n--------------------\n");
    }


    public void afficherLettreClavier(char lettre, String lettresVertes, String lettresJaunes){

        if (lettre == ' '){
            System.out.print("  ");
        }

        else if (lettresVertes.contains(Character.toString(lettre))){
            System.out.print(" " + GRAS + FOND_VERT + TEXTE_NOIR + " " + Character.toUpperCase(lettre) + " " + RESET + " ");
        }

        else if (lettresJaunes.contains(Character.toString(lettre))){
            System.out.print(" " + GRAS + FOND_JAUNE + TEXTE_NOIR + " " + Character.toUpperCase(lettre) + " " + RESET + " ");
        }

        else {
            System.out.print(" " + lettre + "  ");
        }
    }


    public String afficherMot (Word essaiJoueur, String[] resultat){
        String motTableau = "";
        String essai = essaiJoueur.getMot();

        for (int i = 0; i < Word.NB_LETTRES_MAX; i++){

            if (resultat[i].trim().equals("OK")){
                motTableau += "[" + GRAS + FOND_VERT + TEXTE_NOIR + " " + Character.toUpperCase(essai.charAt(i)) + " " + RESET + "] ";
            }

            else if (resultat[i].trim().equals("PRESENT")){
                motTableau += "[" + GRAS + FOND_JAUNE + TEXTE_NOIR + " " + Character.toUpperCase(essai.charAt(i)) + " " + RESET + "] ";
            }

            else {
                motTableau += "[ " + Character.toUpperCase(essai.charAt(i)) + " ] ";
            }
        }

        motTableau += "    ----->     ";
        return motTableau; 
    }


    public String afficherResultatTentative (String [] resultats){
        String afficheResultat = "";

        for (int i = 0; i < resultats.length ; i++) {
            afficheResultat += resultats[i] + "    ";
        }

        return afficheResultat;
    }


    public void afficherGrille(int nbTentatives, ArrayList<Word> tousLesMotsEssais, ArrayList<String []> tousLesResultatsEssais, int lignes){
    System.out.println("\nTentative n° " + nbTentatives + "\n\n");

    for (int i = 0; i < tousLesResultatsEssais.size(); i++) {
        System.out.println(
            afficherMot(
                tousLesMotsEssais.get(i), 
                tousLesResultatsEssais.get(i)) + afficherResultatTentative(tousLesResultatsEssais.get(i))
            );
    }

    afficherGrilleVide(lignes);
}


    public void afficherResultatPartie (Joueur joueur, boolean val, int nbEssais, int points, Word motSecret){

        if (val){
            System.out.println("Bravo " + joueur.getPseudo() + " !!!\n");
            System.out.println("Vous avez trouvé le mot en " + nbEssais + " essais.");
            System.out.println("Vous avez acquis : " + points + " points.");
        }

        else {
            System.out.println("Vous avez utilisé tous vos essais. \nPartie perdue.");
            afficherMotSecret(motSecret);
        }
        
        System.out.println("Votre score total est de : " + joueur.getScore() + " points.\n\n");
    }

    
    public void afficherMotSecret (Word mot){
        System.out.println("Le mot secret était : " + mot.getMot());
    }
    

    public void afficherMessageRecommencerPartie (Joueur joueur){
        System.out.println("\n\nTrès bien " + joueur.getPseudo() + "!!! Recommençons une nouvelle partie !!!");
        System.out.println("Le mot secret a été changé, bonne chance pour le trouver !!!\n\n");
    }
}