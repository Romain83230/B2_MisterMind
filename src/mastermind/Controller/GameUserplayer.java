/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import mastermind.View.*;

/**
 * Code gérant la partie ou l'ordi choisi une combinaison, nous jouons. Les
 * doublons sont possibles
 *
 * @author romain
 */
public class GameUserplayer extends AbstractController {

    int[] valJoueur, valOrdi;
    List liste;
    int[] randomValues = new int[5];
    String result;
    boolean gagne = false;
    int foo = 0;

    /**
     *
     * @param nom
     * @param auth
     */
    public GameUserplayer(String nom, boolean auth) {
        super(nom, auth);
    }

    /**
     *
     */
    @Override
    public void perform() {
        this.setView(new UserplayerView(this));
        this.serieADeviner();
    }

    /**
     * function qui génére les valeurs à deviner, et qui continue le jeu tant que le joueur n'a pas trouvé, qu'il a trouvé, ou qu'il a perdu.
     */
    public void serieADeviner() {
        liste = new LinkedList();
        randomValues = genererFiveValues();
        int finPartie = 0;
        liste.add("                   XXXXX                    ");
        while (finPartie != 10) {
            tourParTout(finPartie);

            if (finPartie == 9) {
                vousAvezPerdu(randomValues);
                break;
            }

            if (gagne) {
                this.getView().send("Félicitation ! Vous avez trouvé la bonne combinaison :) ");
                this.getView().send("C'était bien : " + foo);
                break;
            }
            finPartie++;
            displayJoueurJoue(liste);
        }
    }
    
    
/**
 * Génère un tableau contenant les valeurs aléatoires. 
 * @return 
 */
    public static int[] genererFiveValues() {
        int[] values = new int[5];
        Random rand = new Random();

        for (int i = 0; i < values.length; i++) {
            values[i] = rand.nextInt((8) + 1) + 1;
        }

        return values;
    }

    /**
     * Gére tour par tour l'entrée du joueur, et appelle la fonction qui doit analyser les entrées de l'utilisateurs. 
     * @param tour 
     */
    public void tourParTout(int tour) {
        String choixUser = "";

        this.getView().send("     =============Tour N°" + (tour + 1) + "================");
        this.getView().send("     --> Votre choix : ");
        do {
            choixUser = input.nextLine();
        } while (!checkInput(choixUser));

        foo = Integer.parseInt(choixUser);
        result = checkInput(foo, randomValues);
        liste.add("            " + (tour + 1) + " : " + foo + " => " + result + "   ");

        if ("5B/0N.".equals(result)) {
            gagne = true;
        }
    }

    /**
     * Fonction de test servant à tester ce que l'utilisateur entre. 
     * @param choixUser
     * @return 
     */
    private boolean checkInput(String choixUser) {
        boolean check = false;
        if (choixUser.length() < 5 || choixUser.length() > 5) {
            this.getView().send("Veuillez saisir 5 chiffres");
            return false;
        } else {
            try {
                Integer.parseInt(choixUser);
                check = true;
            } catch (NumberFormatException e) {
                this.getView().send("Veuillez saisir uniquement des chiffres");
                check = false;
            }
        }
        return check;
    }

    /**
     * fonction servant à afficher les résultats. Les strings sont envoyés à la view. 
     * @param val 
     */
    public void displayJoueurJoue(List val) {
        for (int i = 0; i < val.size(); i++) {
            this.getView().send(val.get(i).toString());
        }
    }

    /**
     * Appelé si uniquement le joueur n'a pas réussi à trouver la suite avant la
     * fin des 10 tours.
     */
    private void vousAvezPerdu(int[] val) {
        displayJoueurJoue(liste);
        this.getView().send("=============Vous avez perdu...==============");
        this.getView().send("========La bonne combinaison était :=========");
        String goodAnswer = "";
        for (int i = 0; i < val.length; i++) {
            goodAnswer += (val[i] + " ");
        }
        this.getView().send(goodAnswer);
        this.getView().send("================================================");

//        exit(0);
    }

    /**
     * Check les propositions du joueur. 3 possibilités : aucune bonne réponse.
     * une bonne réponse, mais mal placé une bonne réponse, bien placé
     *
     * @param joueur
     * @param ordi
     * @return
     */
    protected String checkInput(int joueur, int[] ordi) {
        String result = "";
        String number = String.valueOf(joueur);
        char[] digits1 = number.toCharArray();
        int[] choixJoueur = new int[5];
        int blanc = 0;
        int noir = 0;

        // conversion du int basique en tableau de int
        for (int i = 0; i < ordi.length; i++) {
            choixJoueur[i] = Character.getNumericValue(digits1[i]);
        }

        for (int i = 0; i < ordi.length; i++) {
            if (choixJoueur[i] == ordi[i]) {
                blanc++;
                continue;
            }
            for (int j = 0; j < ordi.length; j++) {

                if (choixJoueur[i] == ordi[j] && choixJoueur[j] == ordi[i]) {
                    noir++;
                }
            }
        }
        result = blanc + "B/" + noir + "N.";
        return result;
    }

}
