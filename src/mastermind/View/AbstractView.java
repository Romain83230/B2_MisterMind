/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.View;

import mastermind.Controller.AbstractController;

/**
 * Classe abstraite des vues, contient des méthodes utiles à l'affichage des informations à l'utilisateur.
 * @author ferre
 */
public abstract class AbstractView {
    public AbstractController Action;
    
    /**
     * Constructeur, fixe l'attribut Action qui correspond au contrôleur correspondant à la vue courante.
     * @param controlleur 
     */
    public AbstractView(AbstractController controlleur) {
        this.Action = controlleur;
        this.displayDefaultMessage();
    }
    
    
    /**
     * Affiche un message à l'écran.
     * Étant donné que l'application est ici un programme en console, cette méthode n'est pas différente d'un simple System.out.println().
     * L'avantage majeur ici est que dans le cas où l'on décide de faire une nouvelle interface graphique, seule cette fonction serait à modifier, car toutes les méthodes qui doivent afficher à l'écran utilisent cette méthode.
     * @param message Le message à afficher.
     */
    public void send(String message) {
        System.out.println(message);
    }
    
    /**
     * Méthode abstraite à outrepasser dans les héritiers de cette classe.
     * Affiche le message d'acceuil de la vue.
     */
    public abstract void displayDefaultMessage();
}