/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.View;

import mastermind.Controller.AbstractController;

/**
 *
 * @author ferre
 */
public class UserplayerView extends AbstractView {
    
    /**
     * 
     * @param controlleur 
     */
    public UserplayerView(AbstractController controlleur) {
        super(controlleur);
    }
    
    /**
     * 
     */
    @Override
    public void displayDefaultMessage() {
        System.out.println("Jeu, la machine choisi la combinaison");
    }
}