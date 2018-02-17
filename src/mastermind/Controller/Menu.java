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
public class Menu extends AbstractController{

    public Menu(String nom, boolean auth) {
        super(nom, auth);
    }
    
    @Override
    public void perform() {
        this.setView(new MenuView(this));
        String choix = this.input.nextLine();
        if(this.islogged()) {
            this.decision = choix;
        } else {
            if("1".equals(choix)) {
                this.decision = "7";
            } else if("2".equals(choix)) {
                this.decision = "8";
            } else if("3".equals(choix)) {
                this.decision = "6";
            } else {
                this.decision = "0";
            }
        }
    }
}
