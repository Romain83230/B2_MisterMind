/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;
import java.util.Map;
import java.util.Scanner;
import mastermind.model.DBConnection;
import mastermind.model.Message;

/**
 *
 * @author Romain
 */
public class displayMessage {

    Message message = new Message();
    Scanner sc = new Scanner(System.in);
    DBConnection dbco = new DBConnection();
    
    public displayMessage() {
    }
    
    
    
    public void Welcome() {
        Map<Integer, String> map = message.bienvenue();
        
        
        for (int i = 0; i <= map.size(); i++) {
            System.out.println(map.get(i));
        }
        dbco.Initialize();
        SeConnecter();
        
    }
    
    public void SeConnecter() {
      System.out.println("Veuillez saisir votre login :");
        String login = sc.nextLine();
        System.out.println("Veuillez saisir votre mot de passe :");
        String password = sc.nextLine();
        while(!dbco.VerifConnection(login, password)){
            System.out.println("Veuillez saisir votre login :");
            login = sc.nextLine();
            System.out.println("Veuillez saisir votre mot de passe :");
            password = sc.nextLine();
        }
        //Menu();
    }
    
    public void Menu() {
        Map<Integer, String> map = message.Menu();
        Scanner sc = new Scanner(System.in);
        String choix = "";
        for (int i = 0; i <= map.size(); i++) {
            System.out.println(map.get(i));
        }   
        
        choix = sc.nextLine();
        int choixJoueur = 0;
        try {
            choixJoueur = Integer.valueOf(choix);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        
        if (choixJoueur == (int) choixJoueur) {
            do {
                switch (choixJoueur){
            case 1 :
                System.out.println("Je choisi la combinaison, vous jouez");
                break;
            case 2 :
                System.out.println("Vous choisissez la combinaison, je joue");
                break;
            case 3 :
                System.out.println("Afficher votre profil");
                break;
            case 4 :
                System.out.println("Modifier vos informations");
                break;
            case 5 :
                System.out.println("Afficher les statistiques.");
                break;
            case 6 :
                System.out.println("Quitter");
                break;
            default : 
                System.out.println("Vous n'avez pas choisi une bonne valeur");
                this.Menu();
                break;
            }
        } while (choixJoueur <= 0 || choixJoueur >= 6); 
        }
        
    }
}
