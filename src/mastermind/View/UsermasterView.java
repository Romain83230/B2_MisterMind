/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.View;

import mastermind.Controller.AbstractController;

/**
 * Vue de la fonctionalité 'le joueur génère, l'application cherche'.
 * @author ferre
 */
public class UsermasterView extends AbstractView {

    /**
     * Constructeur manipulant les informations de la classe parente grâce à la méthode super().
     * @param controleur Le contrôleur de la fonctionnalité correspondante.
     */
    public UsermasterView(AbstractController controleur) {
        super(controleur);
    }
    
    /**
     * Affichage des consignes au joueur.
     */
    @Override
    public void displayDefaultMessage() {
        this.send("À vous de jouer!");
        this.send("Choisissez une combinaison, cette combinaison doit être composée de cinq chiffres différents, et ne peut pas contenir de zéros");
        this.send("Envoyez votre réponse au format 'xBP/yMP'");
    }
}