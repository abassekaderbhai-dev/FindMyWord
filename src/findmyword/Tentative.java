package findmyword;

import java.util.ArrayList;

public class Tentative {

    public static final String OK = "OK     ";
    public static final String PRESENT = "PRESENT";
    public static final String ABSENT = "ABSENT ";

    private final ArrayList<Word> tousLesMotsEssaye;
    private final ArrayList <String[]> resultatDeToutesLesTentatives;

    private String clavier;
    private String lettresVertes;
    private String lettresJaunes;
    
    private final Affichage affichage;
    private final Saisie saisie;

    public Tentative (Affichage affichage){
        this.tousLesMotsEssaye = new ArrayList<>();
        this.resultatDeToutesLesTentatives = new ArrayList<>();
        this.clavier = "azertyuiopqsdfghjklmwxcvbn";
        this.lettresVertes = "";
        this.lettresJaunes = "";
        this.saisie = new Saisie();
        this.affichage = affichage;
    }


    public String getClavier(){
        return this.clavier;
    }


    public String getLettresVertes(){
        return this.lettresVertes;
    }


    public String getLettresJaunes(){
        return this.lettresJaunes;
    }


    public ArrayList<Word> getTousLesMotsEssaye(){
        return this.tousLesMotsEssaye;
    }
    

    public Word saisieMotDuJoueur() {

        while (true) {
            System.out.print("\nEntrez un mot de 5 lettres : ");

            String essai = saisie.lireChaine();
            Word motSaisi = new Word(essai);

            if (!motSaisi.longueurMotValide()) {
                System.out.println("Erreur : Le mot doit contenir exactement 5 lettres.\n");
            } 
            
            else if (!motSaisi.contientQueDesLettres()) {
                System.out.println("Erreur : Le mot doit contenir uniquement des lettres.\n");
            } 
            
            else if (!motSaisi.aDesLettresUniques()) {
                System.out.println("Erreur : Le mot ne doit pas contenir de lettres répétées.\n");
            } 
            
            else {
                return motSaisi;
            }
        }
    }

    
    public boolean jouerTentative(Word essaiJoueur, Word motSecret,int nbTentatives, int points){
        String [] tentativeJoueur = analyserTentative(essaiJoueur, motSecret);

        this.resultatDeToutesLesTentatives.add(tentativeJoueur);
        this.tousLesMotsEssaye.add(essaiJoueur);
        
        affichage.afficherGrille(nbTentatives, this.tousLesMotsEssaye, this.resultatDeToutesLesTentatives, points - 1);
        analyserClavier(essaiJoueur, motSecret);
        affichage.afficherClavier(this.clavier, this.lettresVertes, this.lettresJaunes);

        
        for (int i = 0; i < tentativeJoueur.length; i++){
            
            if (! tentativeJoueur[i].equals(OK)){
                return false;
            }
        }

        return true;
    }
    
    
    public String[] analyserTentative(Word mot, Word motSecret){
        String [] resultats = new String[Word.NB_LETTRES_MAX];
        
        for (int i = 0; i<Word.NB_LETTRES_MAX; i++){
        
            if (mot.getLettre(i).equals(motSecret.getLettre(i))){
                resultats[i] = OK;
            }
        
            else if (motSecret.contient(mot.getLettre(i))){
                resultats[i] = PRESENT;
            }
        
            else{
                resultats[i] = ABSENT;
            }
        }
        
        return resultats;
    }
    
    public String analyserClavier(Word mot, Word motSecret){

        for (int i = 0; i < Word.NB_LETTRES_MAX; i++) {
            char lettre = mot.getLettre(i);

            if (this.clavier.contains(Character.toString(lettre))){

                if (mot.getLettre(i).equals(motSecret.getLettre(i))){

                    if (! this.lettresVertes.contains(Character.toString(lettre))){
                        this.lettresVertes += lettre;
                    }
                }

                else if (motSecret.contient(mot.getLettre(i))){

                    if (! this.lettresJaunes.contains(Character.toString(lettre))){
                        this.lettresJaunes += lettre;
                    }
                }

                else {
                    this.clavier = this.clavier.replace(lettre, ' ');
                }
            }
        }
        
        return this.clavier;
    }
}
