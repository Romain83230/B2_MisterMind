package mastermind;

import java.util.Scanner;
import mastermind.Controller.*;

/**
 * Classe principale du programme, initie le jeu, contrôle les fonctionnalités appelées, gère le début et la fin du programme.
 * @author ferre
 */
public class MasterMind {
    static String nomJoueur;
    static AbstractController CurrentAction;
    static boolean logged;
    static String password;
    
    /**
     * Méthode main, lance le jeu.
     * @param args le programme est lancé sans argument
     */
    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("Veuillez entrer votre mot de passe MySQL / MariaDB :");
        Scanner sc = new Scanner(System.in);
        MasterMind.password = sc.nextLine();
        CurrentAction = new Menu("vous n'êtes pas authentifié",false,password);
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
     * Méthode qui lance la construction des objets correspondants en fonction du choix ce l'utilisateur
     * @param choix le numéro correspondant à l'action désirée par le joueur.
     */
    public static void call(String choix) {
        switch(choix) {
            case "1":
                CurrentAction = new GameUserplayer(nomJoueur,logged,MasterMind.password);
              break;
            case "2":
                CurrentAction = new GameUsermaster(nomJoueur,logged,MasterMind.password);
              break;
            case "3":
                CurrentAction = new GetProfil(nomJoueur,logged,MasterMind.password);
              break;
            case "4":
                CurrentAction = new UpdateProfil(nomJoueur,logged,MasterMind.password);
              break;
            case "5":
                CurrentAction = new GetStats(nomJoueur,logged,MasterMind.password);
              break;
            case "7":
                CurrentAction = new Signin(nomJoueur,logged,MasterMind.password);
              break;
            case "8":
                CurrentAction = new Login(nomJoueur,logged,MasterMind.password);
              break;
            default:
                CurrentAction = new Menu(nomJoueur,logged,MasterMind.password);
              break;
        }
    }
}