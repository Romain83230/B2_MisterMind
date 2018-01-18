/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import mastermind.Controller.*;
import static sun.awt.shell.ShellFolder.invoke;

/**
 *
 * @author Romain
 */
public class MasterMind {
    static String nomJoueur;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean exit = false;
        AbstractController Debut = new Menu();
        nomJoueur = Debut.getName();
        int action = Debut.decision;
        while(!exit) {
            if (action == 6) {
                exit = true;
            }
        }
        System.exit(0);
    }
}
