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
    static boolean logged;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean exit = false;
        CurrentAction = new Menu(", vous n'êtes pas authentifié",false);
        while(!exit) {
            int action = CurrentAction.decision;
            nomJoueur = " " + CurrentAction.getName();
            logged = CurrentAction.islogged();
            if (action != 6) {
                call(action);
            } else {
                exit = true;
            }
        }
        System.exit(0);
    }
    
    public static void call(int choix) {
        switch(choix) {
            case 1:
                CurrentAction = new GameUserplayer(nomJoueur,logged);
              break;
            case 2:
                CurrentAction = new GameUsermaster(nomJoueur,logged);
              break;
            case 3:
                CurrentAction = new GetProfil(nomJoueur,logged);
              break;
            case 4:
                CurrentAction = new UpdateProfil(nomJoueur,logged);
              break;
            case 5:
                CurrentAction = new GetStats(nomJoueur,logged);
              break;
            case 7:
                CurrentAction = new Signin(nomJoueur,logged);
              break;
            case 8:
                CurrentAction = new Login(nomJoueur,logged);
              break;
            default:
                CurrentAction = new Menu(nomJoueur,logged);
              break;
        }
    }
}
