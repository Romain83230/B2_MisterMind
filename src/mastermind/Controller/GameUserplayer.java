package mastermind.Controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import mastermind.View.*;

/**
 * Classe correspondant à la fonctionalité 'l'ordinateur génère, le joueur chercher'.
 * @author ferre
 */
public class GameUserplayer extends AbstractController {

    int[] valJoueur, valOrdi;
    List liste;
    int[] randomValues;
    String result;
    boolean gagne;
    int foo = 0;

    /**
     * Constructeur manipulant les informations de la classe parente grâce à la
     * méthode super().
     *
     * @param nom le nom de l'utilisateur.
     * @param auth bolléen, true si l'utilisateur est authentifié, sinon false.
     * @param passwd Le mot de passe MySQL / MariaDB
     */
    public GameUserplayer(String nom, boolean auth, String passwd) {
        super(nom, auth, passwd);
    }

    @Override
    public void perform() {
        this.setView(new UserplayerView(this));
        randomValues = new int[5];
        gagne = false;
        this.serieADeviner();
    }

    /**
     * function qui génére les valeurs à deviner, et qui continue le jeu tant
     * que le joueur n'a pas trouvé, qu'il a trouvé, ou qu'il a perdu.
     */
    public void serieADeviner() {
        liste = new LinkedList();
        randomValues = genererFiveValues();
        int finPartie = 0;
        liste.add("                   XXXXX                    ");
        while (finPartie != 10) {
            tourParTout(finPartie);

            if (finPartie == 9) {
                AjouterStat(finPartie, false);
                vousAvezPerdu(randomValues);
                break;
            }

            if (gagne) {
                this.getView().send("Félicitation ! Vous avez trouvé la bonne combinaison :) ");
                this.getView().send("C'était bien : " + foo);
                AjouterStat(finPartie, true);
                break;
            }
            finPartie++;
            displayJoueurJoue(liste);
        }
    }

    /**
     * Génère un tableau contenant les valeurs aléatoires.
     *
     * @return un tableau de 5 valeurs
     */
    public static int[] genererFiveValues() {
        int[] values = new int[5];
        Random rand = new Random();

        for (int i = 0; i < values.length; i++) {
            values[i] = rand.nextInt((4) + 1) + 1;
        }

        return values;
    }

    /**
     * Gére tour par tour l'entrée du joueur, et appelle la fonction qui doit
     * analyser les entrées de l'utilisateurs.
     *
     * @param tour numéro du tour
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
     *
     * @param choixUser choix menu de l'utilisateur
     * @return true si le choix est bon
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
     * fonction servant à afficher les résultats. Les strings sont envoyés à la
     * view.
     *
     * @param val string a envoyé
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
     * @param joueur Correspond à la suite de nombre entrée par l'utilisateur
     * @param ordi Correspond à la suite de nombre choisi par l'ordinateur
     * @return Le nombre de pions biens placés et mal placés
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

    /**
     * Accède a la base de données pour modifier les statistiques
     *
     * @param nombreCoup nombre de tours du joueur
     * @param gagner true si la partie est gagnée sinon false
     */
    private void AjouterStat(int nombreCoup, boolean gagner) {
        try {
            Database.AddStat(nombreCoup, gagner, this.getName());
        } catch (SQLException ex) {
            Logger.getLogger(GameUserplayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
