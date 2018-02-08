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
public class ProfilView extends AbstractView {

    public ProfilView(AbstractController controlleur) {
        super(controlleur);
    }
    
    @Override
    public void displayDefaultMessage() {
        System.out.println("page de profil");
    }
}