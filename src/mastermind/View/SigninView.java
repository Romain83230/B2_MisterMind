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
public class SigninView extends AbstractView {
    
    /**
     * Constructeur manipulant les informations de la classe parente grâce à la méthode super().
     * @param controleur Le contrôleur de la fonctionnalité correspondante.
     */
    public SigninView(AbstractController controleur) {
        super(controleur);
    }
    
    @Override
    public void displayDefaultMessage() {
        this.send("Page d'inscription");
    }
}