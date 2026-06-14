# FindMyWord

## Présentation

FindMyWord est un jeu développé en Java dans le cadre de la SAÉ de programmation orientée objet.

Le principe est simple : le joueur doit trouver un mot secret de cinq lettres en six tentatives maximum. Après chaque essai, le programme indique si les lettres sont bien placées, présentes mais mal placées, ou absentes du mot secret.

## Organisation du projet

```text
FindMyWord/
├── src/       classes Java du jeu
├── tests/     tests unitaires
├── data/      fichier des mots
├── jar/       librairie utilisée pour lire les mots
└── README.md
```

## Compilation et lancement du jeu

Depuis le dossier principal du projet, utiliser la commande suivante :

```bash
javac -encoding UTF-8 -cp jar/wordset.jar -d out src/findmyword/*.java
java -cp out:jar/wordset.jar findmyword.Main
```

Sur Windows PowerShell :

```powershell
javac -encoding UTF-8 -cp "jar/wordset.jar" -d out src\findmyword\*.java
java -cp "out;jar/wordset.jar" findmyword.Main
```


## Lancement des tests

Sur Windows PowerShell :

```powershell
javac -encoding UTF-8 -cp "jar/wordset.jar" -d out src\findmyword\*.java tests\findmyword\TestUnitaire.java
java -cp "out;jar/wordset.jar" findmyword.TestUnitaire
```

Sur Linux ou Mac :

```bash
javac -encoding UTF-8 -cp jar/wordset.jar -d out src/findmyword/*.java tests/findmyword/TestUnitaire.java
java -cp out:jar/wordset.jar findmyword.TestUnitaire
```

## Fonctionnalités principales

Le programme permet de :

* saisir le pseudo du joueur ;
* choisir un mot secret depuis un fichier JSON ;
* vérifier si une tentative est valide ;
* analyser chaque lettre avec OK, PRESENT ou ABSENT ;
* afficher une grille de jeu ;
* afficher un clavier mis à jour ;
* gérer la victoire et la défaite ;
* proposer de recommencer une partie ;
* lancer des tests unitaires simples.

## Remarque

Le programme doit être lancé depuis le dossier principal du projet afin que les chemins vers `data/mots.json` et `jar/wordset.jar` fonctionnent correctement.
