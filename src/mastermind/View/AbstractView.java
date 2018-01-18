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
    private AbstractController Action;
    
    public AbstractView() {
        this.displayMessage();
    }
    
    public abstract void displayMessage();
}