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
 *
 * @author ferre
 */
public class GameUserplayer extends AbstractController{
    boolean integrity;
    int[] valJoueur, valOrdi;
    List liste ;
    int[] randomValues = new int[5];
    String result;
    boolean gagne = false;
    int foo = 0;

    public GameUserplayer(String nom, boolean auth) {
        super(nom, auth);
    }
    @Override
    public void perform() {
        this.setView(new UserplayerView(this));
        this.serieADeviner();
    }
    
        public String checkintegrity(char[] tableau) {
        
        if(tableau.length != 5) {
            this.integrity = false;
            return "La combinaison rentrée n'a pas la bonne longueur.";
        }
        
        
        for(int i = 0; i < tableau.length; i++ ) {
            int a = Character.getNumericValue(tableau[i]);
            if(a < 1 || a > 9){
                this.integrity = false;
                return "Vous devez n'entrer que des chiffres entre 1 et 9.";
            }
        }
        
        char[] alreadyused = {tableau[0],'0','0','0','0'};
        for(int i = 1; i < tableau.length - 1; i++ ) {
            for(int j = 0; j < alreadyused.length; j++) {
                if(tableau[i] == alreadyused[j]){
                    this.integrity = false;
                    return "Chaque chiffre droit être différent.";
                }
                alreadyused[i] = tableau[i - 1];
            }
            
        }
        this.integrity = true;
        return "";
    }
        
        
        
        
        
         public void serieADeviner() {
        liste = new LinkedList();
        randomValues = genererFourValues();
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
         
         public static int[] genererFourValues(){
        int[] values = new int[5];
        Random rand = new Random();
        
        for (int i = 0; i < values.length; i++) {
            values[i] = rand.nextInt((4) + 1) + 1;
        }
        
        return values;
    }
    
    
    public void tourParTout(int tour) {
        String choixUser = "";

        this.getView().send("     =============Tour N°" + (tour + 1) + "================");
        this.getView().send("     --> Votre choix : ");
        do {
            choixUser = input.nextLine();
        } while (!checkInput(choixUser));

        foo = Integer.parseInt(choixUser);
        result = checkInput(foo, randomValues);           
        liste.add("            "+(tour + 1)+" : " + foo + " => " + result +  "   ");

        if ("5B/0N.".equals(result)) {
            gagne = true;
        }
    }
    
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
        this.getView().send("                    ");
        for (int i = 0; i < val.length; i++) {
            this.getView().send(val[i] + " ");
        }
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
