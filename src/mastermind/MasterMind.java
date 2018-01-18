/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import mastermind.Controller.*;

/**
 *
 * @author Romain
 */
public class MasterMind {
    static String nomJoueur;
    static AbstractController CurrentAction;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean exit = false;
        CurrentAction = new Menu();
        nomJoueur = CurrentAction.getName();
        while(!exit) {
            int action = CurrentAction.decision;
            //System.out.println(action);
            if (action == 6) {
                exit = true;
            } else {
                call(action);
            }
        }
        System.exit(0);
    }
    
    public static void call(int choix) {
        switch(choix) {
            case 1:
                CurrentAction = new GameUserplayer();
              break;
            case 2:
                CurrentAction = new GameUsermaster();
              break;
            case 3:
                CurrentAction = new GetProfil();
              break;
            case 4:
                CurrentAction = new UpdateProfil();
              break;
            case 5:
                CurrentAction = new GetStats();
              break;
            case 7:
                CurrentAction = new Signin();
              break;
            case 8:
                CurrentAction = new Login();
              break;
            default:
                CurrentAction = new Menu();
              break;
        }
    }
}
