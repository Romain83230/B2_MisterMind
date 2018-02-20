/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.View;

import mastermind.Controller.AbstractController;

/**
 * Vue de la fonctionalité Menu
 * @author ferre
 */
public class MenuView extends AbstractView {

    /**
     * Constructeur manipulant les informations de la classe parente grâce à la méthode super().
     * @param controleur Le contrôleur de la fonctionnalité correspondante.
     */
    public MenuView(AbstractController controleur) {
        super(controleur);
    }
    
    /**
     * Affiche le menu en y insérant le nom de l'utilisateur. Si le joueur est identifié, il n'aura pas le même menu.
     */
    @Override
    public void displayDefaultMessage() {
        this.send("Bonjour, " + Action.getName() + "!");
        
        if(this.Action.islogged()) {
            this.send("1 : Je choisis la combinaison, vous jouez");
            this.send("2 : Vous choisissez la combinaison, je joue");
            this.send("3 : Afficher votre profil");
            this.send("4 : Modifier vos informations");
            this.send("5 : Afficher les statistiques");
            this.send("6 : quitter");
        } else {
            this.send("1 : Je m'inscris");
            this.send("2 : Je me connecte");
            this.send("3 : quitter");
        }
            

    }
}