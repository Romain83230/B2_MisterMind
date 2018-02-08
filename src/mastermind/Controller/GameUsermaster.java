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
            
            this.input.nextLine();
        }
    }
    
}
