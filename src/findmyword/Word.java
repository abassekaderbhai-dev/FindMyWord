package findmyword;

public class Word {
    private final String mot;
    public static final int NB_LETTRES_MAX = 5;

    public Word (String mot){
        this.mot = mot.trim().toLowerCase();
    }


    public String getMot (){
        return this.mot;
    }


    public Character getLettre(int index){
        return this.mot.charAt(index);
    }


    public boolean contient(char lettre){
        return this.mot.indexOf(lettre) != -1;  
    }


    public boolean longueurMotValide (){
        return this.mot.length() == NB_LETTRES_MAX;
    }


    public boolean aDesLettresUniques (){

        for (int i=0 ; i<this.mot.length() ; i++){

            for (int j=i+1 ; j<this.mot.length() ; j++){

                if (this.mot.charAt(i) == this.mot.charAt(j)){
                    return false;
                }
            }
        }

        return true;
    }


    public boolean contientQueDesLettres (){

        for (int i=0 ; i<this.mot.length() ; i++){
    
            if (! Character.isLetter(this.mot.charAt(i))){
                return false;
            }
        }
        
        return true;
    }


    public boolean estValide (){
        return (longueurMotValide() && aDesLettresUniques() && contientQueDesLettres());
    }   
}