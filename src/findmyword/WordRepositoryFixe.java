package findmyword;

public class WordRepositoryFixe implements WordRepository {
    private final String motSecret;

    public WordRepositoryFixe(String mot) {
        this.motSecret = mot;
    }

    @Override
    public Word getMotSecret() {
        return new Word(this.motSecret);
    }   

    @Override
    public boolean motDansLaListe(String mot){
        return true;
    }
}
