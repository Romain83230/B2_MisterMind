package mastermind;

import mastermind.Controller.*;

/**
 * Classe principale du programme, initie le jeu, contrôle les fonctionnalités appelées, gère le début et la fin du programme.
 * @author ferre
 */
public class MasterMind {
    static String nomJoueur;
    static AbstractController CurrentAction;
    static boolean logged;
    
    /**
     * Méthode main, lance le jeu.
     * @param args le programme est lancé sans argument
     */
    public static void main(String[] args) {
        boolean exit = false;
        CurrentAction = new Menu(", vous n'êtes pas authentifié",false);
        while(!exit) {
            String action;
            try{
                action = CurrentAction.decision.substring(0, 1);
            } catch(Exception e) {
                action = "";
            }
            nomJoueur = CurrentAction.getName();
            logged = CurrentAction.islogged();
            if (!"6".equals(action)) {
                call(action);
            } else {
                exit = true;
            }
        }
        System.exit(0);
    }
    
    /**
     * Méthode qui lance les objets correspondants en fonction
     * @param choix
     */
    public static void call(String choix) {
        switch(choix) {
            case "1":
                CurrentAction = new GameUserplayer(nomJoueur,logged);
              break;
            case "2":
                CurrentAction = new GameUsermaster(nomJoueur,logged);
              break;
            case "3":
                CurrentAction = new GetProfil(nomJoueur,logged);
              break;
            case "4":
                CurrentAction = new UpdateProfil(nomJoueur,logged);
              break;
            case "5":
                CurrentAction = new GetStats(nomJoueur,logged);
              break;
            case "7":
                CurrentAction = new Signin(nomJoueur,logged);
              break;
            case "8":
                CurrentAction = new Login(nomJoueur,logged);
              break;
            default:
                CurrentAction = new Menu(nomJoueur,logged);
              break;
        }
    }
}