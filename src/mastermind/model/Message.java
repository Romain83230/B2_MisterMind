/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Romain
 */
public class Message {
     Map<Integer, String> message = new HashMap<>();

    public Message() {
    }
    
     
    
    public Map<Integer, String> bienvenue() {
       
        
        message.put(1, "==========================================================================");
        message.put(2, "***************** BIENVENUE DANS LE JEU DU MASTER MIND *********************");
        message.put(3, "==========================================================================");         
        
        return message;
    }
    
    public Map<Integer, String> Menu() {
        Map<Integer, String> menu = new HashMap<>();
        
        String nom = "ROMAIN";
        menu.put(1, "\n");
        menu.put(2, "********************** BIENVENUE "+ nom + " **************************");
        menu.put(3, "\n");   
        menu.put(4, "1 : Je choisi la combinaison, vous jouez.\n");
        menu.put(5, "2 : Vous choisissez la combinaison, je joue.\n");
        menu.put(6, "3 : Afficher votre profil.\n");
        menu.put(7, "4 : Modifier vos informations.\n");
        menu.put(8, "5 : Afficher les statistiques.\n");
        menu.put(9, "6 : Quitter.\n");
        
        return menu;
    }
    
    
    
}
