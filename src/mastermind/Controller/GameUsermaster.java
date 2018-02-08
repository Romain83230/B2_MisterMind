package mastermind.Controller;

import mastermind.View.*;

/**
 *
 * @author ferre
 */
public class GameUsermaster extends AbstractController {
    String combinaison;
    boolean integrity;

    public GameUsermaster(String nom, boolean auth) {
        super(nom, auth);
    }
    @Override
    public void perform() {
        this.setView(new UsermasterView(this));
        while(!this.integrity){
            combinaison = this.input.nextLine();
            char[] adeviner = combinaison.toCharArray();
            
            this.getView().send(this.checkintegrity(adeviner));
            this.input.nextLine();
        }
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
        return "Combinaison acceptée, début de la partie.";
    }
}
