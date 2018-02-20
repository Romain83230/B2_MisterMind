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
public class MenuView extends AbstractView {

    public MenuView(AbstractController controlleur) {
        super(controlleur);
    }
    
    @Override
    public void displayDefaultMessage() {
        System.out.println("Bonjour " + Action.getName());
        
        if(this.Action.islogged()) {
            System.out.println("1 : Je choisis la combinaison, vous jouez");
            System.out.println("2 : Vous choisissez la combinaison, je joue");
            System.out.println("3 : Afficher votre profil");
            System.out.println("4 : Modifier vos informations");
            System.out.println("5 : Afficher les statistiques");
            System.out.println("6 : quitter");
        } else {
            System.out.println("1 : Je m'inscris");
            System.out.println("2 : Je me connecte");
            System.out.println("3 : quitter");
        }
            

    }
}