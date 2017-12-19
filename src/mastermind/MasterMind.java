/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Romain
 */
public class MasterMind {

    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        // TODO code application logic here
        /*System.out.println("Veuillez saisir un mot :");
        String entree = sc.nextLine();
        System.out.println("Vous avez saisi : " + entree);*/
        DBConnection dbco = new DBConnection();
        dbco.Initialize();
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
    }
    
}
