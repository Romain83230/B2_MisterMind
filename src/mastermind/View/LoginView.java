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
public class LoginView extends AbstractView {

    public LoginView(AbstractController controlleur) {
        super(controlleur);
    }
    
    @Override
    public void displayMessage() {
        System.out.println("page de login");
    }
}