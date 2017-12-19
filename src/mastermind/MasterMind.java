/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import mastermind.model.Message;
import java.util.Map;

/**
 *
 * @author Romain
 */
public class MasterMind {

    Message message = new Message();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        displayMessage welcome = new displayMessage();
        welcome.Welcome();
        welcome.Menu();
    }
}
