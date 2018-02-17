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
        System.out.println("Choisissez une combinaison, cette combinaison doit être composée de cinq chiffres différents, et ne peut pas contenir de zéros.");
        System.out.println("Notez la combinaison de coté.");
        System.out.println("");
        System.out.println("Je vais vous demander les combinaisons, vous devrez y répondre en indiquant le nombre de bons placements et le nombre de chiffres présents mais mal placés sous le format xBP/yMP");
    }
}