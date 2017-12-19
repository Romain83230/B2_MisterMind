/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.util.Random;

/**
 *
 * @author Romain
 */
public class randomValues {
    
    
    public static int[] genererFourValues(){
        int[] values = new int[5];
        Random rand = new Random();
        
        for (int i = 0; i < 5; i++) {
            values[i] = rand.nextInt((4) + 1) + 1;
        }
        
        return values;
    }
    
}
