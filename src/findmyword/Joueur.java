package findmyword;

public class Joueur {
    private final Saisie saisie;
    private String pseudo;
    private int score;

    public Joueur (){
        this.saisie = new Saisie();
        this.score = 0;
    }


    public String pseudoSaisie(){
        System.out.print("Entrez votre pseudo : ");
        this.pseudo = saisie.lireChaine();
        return this.pseudo;
    }


    public String getPseudo (){
        return this.pseudo;
    }


    public int getScore (){
        return this.score;
    }

    
    public int ajouterPoints (int points){
        return this.score += points;
    }


}
