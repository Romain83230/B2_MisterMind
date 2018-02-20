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
public class UsermasterView extends AbstractView {

    public UsermasterView(AbstractController controlleur) {
        super(controlleur);
    }
    
    @Override
    public void displayDefaultMessage() {
        System.out.println("À vous de jouer!");
        System.out.println("Choisissez une combinaison, cette combinaison doit être composée de cinq chiffres différents, et ne peut pas contenir de zéros");
    }
}