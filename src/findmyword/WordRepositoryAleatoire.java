package findmyword;

import java.io.IOException;
import words.JsonWordSet;
import words.WordSet;

public class WordRepositoryAleatoire implements WordRepository {

    private final WordSet mots;

    public WordRepositoryAleatoire(String cheminFichier) throws IOException {  
        this.mots = new JsonWordSet(cheminFichier);
    }

    @Override
    public Word getMotSecret() {
        return new Word(this.mots.random());
    }

    @Override
    public boolean motDansLaListe (String mot){

        for (int i = 0; i<mots.size(); i++){
            
            if (mots.word(i).equals(mot)){
                return true;
            }
        }
        
        return false;
    }
}