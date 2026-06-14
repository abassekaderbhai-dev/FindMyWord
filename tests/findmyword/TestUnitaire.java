package findmyword;

public class TestUnitaire {

    private static int nbTests = 0;
    private static int nbTestsReussis = 0;


    public static void afficherResultat(boolean condition){
        nbTests++;

        if (condition){
            nbTestsReussis++;
            System.out.println("Résultat : réussi");
        }

        else {
            System.out.println("Résultat : échoué");
        }

        System.out.println();
    }


    public static String tableauEnTexte(String[] tableau){
        String texte = "";

        for (int i = 0; i < tableau.length; i++){
            texte = texte + tableau[i].trim();

            if (i < tableau.length - 1){
                texte = texte + " ";
            }
        }

        return texte;
    }


    public static void verifierValidationMot(String nomTest, String mot, boolean attendu, boolean obtenu){
        System.out.println(nomTest);
        System.out.println("Mot testé : " + mot);
        System.out.println("Attendu : " + attendu);
        System.out.println("Obtenu : " + obtenu);

        afficherResultat(attendu == obtenu);
    }


    public static void verifierAnalyse(String nomTest, String motTeste, String motSecret, String attendu, String obtenu){
        System.out.println(nomTest);
        System.out.println("Mot secret : " + motSecret);
        System.out.println("Mot testé  : " + motTeste);
        System.out.println("Attendu    : " + attendu);
        System.out.println("Obtenu     : " + obtenu);

        afficherResultat(attendu.equals(obtenu));
    }


    public static void testerClasseWord(){
        System.out.println("\n==============================");
        System.out.println("   Tests de la classe Word");
        System.out.println("==============================\n");

        Word mot1 = new Word(" liGne ");
        System.out.println("Test 1 : nettoyage du mot");
        System.out.println("Mot de départ : \" liGne \"");
        System.out.println("Attendu : ligne");
        System.out.println("Obtenu  : " + mot1.getMot());
        afficherResultat(mot1.getMot().equals("ligne"));

        Word mot2 = new Word("chat");
        verifierValidationMot(
            "Test 2 : mot trop court",
            "chat",
            false,
            mot2.longueurMotValide()
        );

        Word mot3 = new Word("avions");
        verifierValidationMot(
            "Test 3 : mot trop long",
            "avions",
            false,
            mot3.longueurMotValide()
        );

        Word mot4 = new Word("ab1de");
        verifierValidationMot(
            "Test 4 : mot avec un chiffre",
            "ab1de",
            false,
            mot4.contientQueDesLettres()
        );

        Word mot5 = new Word("belle");
        verifierValidationMot(
            "Test 5 : mot avec des lettres répétées",
            "belle",
            false,
            mot5.aDesLettresUniques()
        );

        Word mot6 = new Word("ligne");
        verifierValidationMot(
            "Test 6 : mot correct",
            "ligne",
            true,
            mot6.estValide()
        );
    }


    public static void testerAnalyseTentative(){
        System.out.println("\n==============================");
        System.out.println(" Tests de l'analyse des mots");
        System.out.println("==============================\n");

        
        Affichage affichage = new Affichage();
        Tentative tentative = new Tentative(affichage);

        Word motSecret = new Word("ligne");

        String[] resultat1 = tentative.analyserTentative(new Word("ligne"), motSecret);
        verifierAnalyse(
            "Test 1 : mot complètement correct",
            "ligne",
            "ligne",
            "OK OK OK OK OK",
            tableauEnTexte(resultat1)
        );

        String[] resultat2 = tentative.analyserTentative(new Word("genil"), motSecret);
        verifierAnalyse(
            "Test 2 : toutes les lettres sont présentes mais mal placées",
            "genil",
            "ligne",
            "PRESENT PRESENT PRESENT PRESENT PRESENT",
            tableauEnTexte(resultat2)
        );

        String[] resultat3 = tentative.analyserTentative(new Word("table"), motSecret);
        verifierAnalyse(
            "Test 3 : mélange entre lettres absentes, présentes et bien placées",
            "table",
            "ligne",
            "ABSENT ABSENT ABSENT PRESENT OK",
            tableauEnTexte(resultat3)
        );

        String[] resultat4 = tentative.analyserTentative(new Word("lampe"), motSecret);
        verifierAnalyse(
            "Test 4 : certaines lettres sont bien placées et d'autres absentes",
            "lampe",
            "ligne",
            "OK ABSENT ABSENT ABSENT OK",
            tableauEnTexte(resultat4)
        );
    }


    public static void testerClavier(){
        System.out.println("\n==============================");
        System.out.println("      Tests du clavier");
        System.out.println("==============================\n");

        Affichage affichage = new Affichage();
        Tentative tentative = new Tentative(affichage);

        Word motSecret = new Word("ligne");
        Word essai = new Word("table");

        String clavier = tentative.analyserClavier(essai, motSecret);

        System.out.println("Test 1 : clavier après une tentative");
        System.out.println("Mot secret : ligne");
        System.out.println("Mot testé  : table");
        System.out.println();

        System.out.println("Analyse attendue :");
        System.out.println("- t, a et b doivent disparaître car ils sont absents");
        System.out.println("- l doit être en jaune car il est présent mais mal placé");
        System.out.println("- e doit être en vert car il est bien placé");
        System.out.println();

        System.out.println("Clavier obtenu :");
        affichage.afficherClavier(clavier, tentative.getLettresVertes(), tentative.getLettresJaunes());

        System.out.println("Lettres vertes attendues : e");
        System.out.println("Lettres vertes obtenues  : " + tentative.getLettresVertes());
        System.out.println();

        System.out.println("Lettres jaunes attendues : l");
        System.out.println("Lettres jaunes obtenues  : " + tentative.getLettresJaunes());
        System.out.println();

        boolean testClavier = tentative.getLettresVertes().contains("e")
                            && tentative.getLettresJaunes().contains("l")
                            && ! clavier.contains("t")
                            && ! clavier.contains("a")
                            && ! clavier.contains("b");

        afficherResultat(testClavier);
    }


    public static void testerWordRepositoryFixe(){
        System.out.println("\n==============================");
        System.out.println(" Tests de WordRepositoryFixe");
        System.out.println("==============================\n");

        WordRepository repo = new WordRepositoryFixe("ligne");

        System.out.println("Test 1 : récupération du mot secret fixe");
        System.out.println("Attendu : ligne");
        System.out.println("Obtenu  : " + repo.getMotSecret().getMot());
        afficherResultat(repo.getMotSecret().getMot().equals("ligne"));

        System.out.println("Test 2 : vérification d'un mot dans le repository fixe");
        System.out.println("Mot testé : table");
        System.out.println("Attendu : true");
        System.out.println("Obtenu  : " + repo.motDansLaListe("table"));
        afficherResultat(repo.motDansLaListe("table"));
    }


    public static void main(String[] args) {

        testerClasseWord();
        testerAnalyseTentative();
        testerClavier();
        testerWordRepositoryFixe();

        System.out.println("\n==============================");
        System.out.println("       Bilan des tests");      
        System.out.println("==============================");
        System.out.println("Tests réussis : " + nbTestsReussis + " / " + nbTests);
    }
}