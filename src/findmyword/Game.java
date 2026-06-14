package findmyword;

public class Game {

    public static final int MAX_TENTATIVES = 6;
    private int nbTentatives;
    private int points;

    private boolean gagnant;
    private final WordRepository repo;
    private final Joueur joueur;
    
    private final Affichage affichage;
    private Tentative tentative;
    private Word motSecret;
    private final Saisie saisie;
       

    public Game(WordRepository repo, Joueur joueur) {
        this.nbTentatives = 1;
        this.points = MAX_TENTATIVES + 1;
        this.gagnant = false;        
        this.repo = repo;
        this.joueur = joueur;
        this.affichage = new Affichage();
        this.tentative = new Tentative(this.affichage);
        this.motSecret = repo.getMotSecret();
        this.saisie = new Saisie();
    }


    public boolean getGagnant(){
        return this.gagnant;
    }


    public boolean motDansLaListe(String mot){
        return this.repo.motDansLaListe(mot);
    }

    
    public void lancerPartie(boolean premierePartie ){

        if (premierePartie){
            affichage.afficherDebutPartie(this.joueur);
        }

        affichage.afficherGrilleVide(MAX_TENTATIVES + 1);
        affichage.afficherClavier(tentative.getClavier(), tentative.getLettresVertes(), tentative.getLettresJaunes());
        
        lancerTour();

        if (! this.gagnant){
            this.points = 0;
        }

        joueur.ajouterPoints(this.points);
        affichage.afficherResultatPartie(this.joueur,this.gagnant, nbTentatives-1, this.points, this.motSecret);
        recommencerPartie();
    }

    public void lancerTour() {

        while (nbTentatives <= MAX_TENTATIVES && !this.gagnant) {
            Word essai = tentative.saisieMotDuJoueur();

            if (motDansLaListe(essai.getMot())) {
                boolean gagne = tentative.jouerTentative(essai, this.motSecret, this.nbTentatives, this.points);

                if (gagne) {
                    this.gagnant = true;
                }

                this.points--;
                this.nbTentatives++;
            } 
            
            else {
                System.out.println("Le mot saisi ne fait pas partie de la liste de mots.\n");
            }
        }
    }

    public char demanderRecommencerPartie(){
        System.out.println("Voulez vous recommencer la partie ? \nSi oui tapez O sinon N.");

        String reponse = saisie.lireChaine().trim();

        if (reponse.length() == 0){
            return ' ';
        }

        return reponse.charAt(0);
    }


    public void recommencerPartie(){

        while (true){
            char reponse = demanderRecommencerPartie();

            if (reponse == 'O' || reponse == 'o'){
                reinitialiserVariable();
                return;
            }

            else if (reponse == 'N' || reponse == 'n'){
                System.out.println("\n\nDans ce cas je vous dis à bientôt pour une nouvelle partie !!!\n\n");
                return;
            }

            else {
                System.out.println("\nRéponse invalide. Tapez O pour oui ou N pour non.");
            }
        }
    }


    public void reinitialiserVariable(){
        this.nbTentatives = 1;
        this.points = MAX_TENTATIVES + 1;
        this.motSecret = repo.getMotSecret();
        this.tentative = new Tentative(this.affichage);
        this.gagnant = false;
        affichage.afficherMessageRecommencerPartie(joueur);
        lancerPartie(false);
    }
}


