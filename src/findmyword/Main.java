package findmyword;

public class Main {

    public static void main(String[] args) {
        
        try {
            WordRepository repo = new WordRepositoryAleatoire("data/mots.json");
            Joueur joueur = new Joueur();
            Game game = new Game(repo, joueur);
            game.lancerPartie(true);
        } 
        
        catch (java.io.FileNotFoundException e) {
            System.out.println("Erreur : impossible de lancer le jeu, je ne trouve pas le fichier de mots.");
            System.out.println(e.getMessage());
        } 

        catch (java.io.IOException e) {
            System.out.println("Erreur : impossible de lancer le jeu, une erreur est survenue lors de la lecture du fichier de mots.");
            System.out.println(e.getMessage());
        }
    }
}