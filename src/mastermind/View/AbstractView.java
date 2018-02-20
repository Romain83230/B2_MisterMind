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
public abstract class AbstractView {
    public AbstractController Action;
    
    public AbstractView(AbstractController controlleur) {
        this.Action = controlleur;
        this.displayDefaultMessage();
    }
    
    public void send(String message) {
        System.out.println(message);
        System.out.println();
    }
    
    public abstract void displayDefaultMessage();
}