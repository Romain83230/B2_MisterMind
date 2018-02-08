/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.Controller;
import mastermind.View.*;

/**
 *
 * @author ferre
 */
public class GameUserplayer extends AbstractController{
    boolean integrity;

    public GameUserplayer(String nom, boolean auth) {
        super(nom, auth);
    }
    @Override
    public void perform() {
        this.setView(new UserplayerView(this));
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
}
