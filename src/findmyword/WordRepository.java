package findmyword;

public interface  WordRepository {

    public Word getMotSecret();
    
    public boolean motDansLaListe(String mot);
}