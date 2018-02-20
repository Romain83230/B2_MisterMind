package mastermind.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import mastermind.View.*;

/**
 *
 * @author ferre
 */
public class GameUsermaster extends AbstractController {
    String combinaison;
    ArrayList permutations;
    ArrayList entrees;
    ArrayList guesses;
    
    public GameUsermaster(String nom, boolean auth) {
        super(nom, auth);
    }

    @Override
    public void perform() {
        this.setView(new UsermasterView(this));

        permutations = initArray();
        entrees = new ArrayList(0);
        guesses = new ArrayList(0);
        String guess;
        String reponse;
        boolean found = false;
        
        ArrayList restants = this.permutations;
        for(int i = 0; i < 10; i++) {
            this.getView().send("Tour " + (i + 1));
            
            guess = (String) restants.get(0); 
            this.guesses.add(guess);
            this.getView().send("Je propose : " + guess);
            reponse = this.input.nextLine();

            while(this.verif(reponse)) {
                this.getView().send("Vous devez envoyer votre réponse au format 'xBP/yMP'");
                reponse = this.input.nextLine();
            }
                        
            if(reponse.substring(0, 7).equals("5BP/0MP")){
                found = true;
                break;
            }
            int BP = Integer.parseInt(reponse.substring(0, 1));
            int MP = Integer.parseInt(reponse.substring(4, 5));
            
            int[] tampon = {BP,MP};
            this.entrees.add(tampon);
            
            restants = this.getRestants(guess, BP, MP, restants);
            
            if(restants.isEmpty()){
                found = false;
                this.getView().send("J'abandonne...");
                break;
            }
        }
        
        if(found) {
            this.getView().send("J'ai gagné! Vous aurez plus de chance la prochaine fois");
        } else {
            this.getView().send("J'ai perdu! Quelle était la bonne réponse?");
            reponse = this.input.nextLine();
            this.checkmistakes(reponse);
        }
    }
     
    /**
     * 
     * @return 
     */
    private ArrayList initArray() {
        
        ArrayList chiffres = new ArrayList(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
        ArrayList result = new ArrayList(0);
        
        for(int i1 = 0; i1 < 9; i1++) {
            for(int i2 = 0; i2 < 9; i2++) {
                if(chiffres.get(i1) != chiffres.get(i2)) {
                    for(int i3 = 0; i3 < 9; i3++) {
                        if(chiffres.get(i1) != chiffres.get(i3) && chiffres.get(i2) != chiffres.get(i3)) {
                            for(int i4 = 0; i4 < 9; i4++) {
                                if(chiffres.get(i1) != chiffres.get(i4) && chiffres.get(i2) != chiffres.get(i4) && chiffres.get(i3) != chiffres.get(i4)) {
                                    for(int i5 = 0; i5 < 9; i5++) {
                                        if(chiffres.get(i1) != chiffres.get(i5) && chiffres.get(i2) != chiffres.get(i5) && chiffres.get(i3) != chiffres.get(i5) && chiffres.get(i4) != chiffres.get(i5)) {
                                            result.add("" + chiffres.get(i1) + chiffres.get(i2) + chiffres.get(i3) + chiffres.get(i4) + chiffres.get(i5));
                                        }
                                    }                                
                                }
                            }
                        }
                    }                    
                }
            }
        }
        return result;
    }
    private void checkmistakes(String string) {
        char[] tableau = string.toCharArray();
        if(tableau.length != 5) {
            this.getView().send("La combinaison que vous avez rentré n'a pas la bonne longueur... Maintenant je boude!");
            return;
        }
        
        
        for(int i = 0; i < tableau.length; i++ ) {
            int a = Character.getNumericValue(tableau[i]);
            if(a < 1 || a > 9){
                this.getView().send("Vous êtes sensé n'entrer que des chiffres entre 1 et 9... Maintenant je boude!");
                return;
            }
        }
        
        char[] alreadyused = {tableau[0],'0','0','0','0'};
        for(int i = 1; i < tableau.length - 1; i++ ) {
            for(int j = 0; j < alreadyused.length; j++) {
                if(tableau[i] == alreadyused[j]){
                    this.getView().send("Chaque chiffre droit être différent... Maintenant je boude!");
                    return;
                }
                alreadyused[i] = tableau[i - 1];
            }
        }
        
        for(int i = 0; i < this.guesses.size(); i++) {
            if(string.equals((String)this.guesses.get(i))){
                this.getView().send("J'avais donné cette réponse... Maintenant je boude!");
                return;
            }
        }
        
        for(int i = 0; i < this.entrees.size(); i++) {
            if(!Arrays.equals((int[]) this.entrees.get(i), this.getDifferences(string, (String) this.guesses.get(i)))){
                
                
                this.getView().send("Vous ne m'avez pas donné la bonne indication lors du tour " + (i + 1) + "... Maintenant je boude!");
                return;
            }
        }
    }

    /**
     * 
     * @param guess
     * @param BP
     * @param MP
     * @param restants
     * @return 
     */
    private ArrayList getRestants(String guess,int BP, int MP, ArrayList restants) {
        ArrayList possibles = new ArrayList(0);
        int[] differences;
        for(int i = 0; i < restants.size(); i++) {
            differences = this.getDifferences(guess, (String) restants.get(i));
            if(BP == differences[0] && MP == differences[1]) {
                possibles.add(restants.get(i));
            }
        }
        return possibles;
    }
    /**
     * 
     * @param guess
     * @param response
     * @return 
     */
    private int[] getDifferences(String guess, String response){
        int[] renvoi = {0,0};
        String[] tableGuess = guess.split("");
        String[] tableResponse = response.split("");
        for(int i = 0; i < 5; i++) {
            if(tableGuess[i].equals(tableResponse[i])) {
                renvoi[0]++;
            }
            for(int j = 0; j < 5; j++) {
                if(tableGuess[i].equals(tableResponse[j]) && i != j) {
                    renvoi[1]++;
                }
            }
        }
        return renvoi;
    }

    /**
     * 
     * @param reponse
     * @return 
     */
    private boolean verif(String reponse) {
            if(reponse.length() > 6) {
                if(reponse.substring(1, 4).equals("BP/") && reponse.substring(5, 7).equals("MP")) {
                    try{
                        Integer.parseInt(reponse.substring(0, 1));
                        Integer.parseInt(reponse.substring(4, 5));
                        return false;
                    } catch(NumberFormatException e) {
                        return true;
                    }
                }
            }
        return true;
    }
}