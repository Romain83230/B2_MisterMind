/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.View;

import mastermind.Controller.AbstractController;

/**
 * Vue de la fonctionalité 'mise à jour du profil'.
 * @author ferre
 */
public class UpdateprofilView extends AbstractView {

    /**
    * Constructeur manipulant les informations de la classe parente grâce à la méthode super().
    * @param controleur Le contrôleur de la fonctionnalité correspondante.
    */
    public UpdateprofilView(AbstractController controleur) {
        super(controleur);
    }
    
    /**
     * Simple message.
     */
    @Override
    public void displayDefaultMessage() {
        this.send("");
        this.send("Voici vos informations actuelles");
    }
}