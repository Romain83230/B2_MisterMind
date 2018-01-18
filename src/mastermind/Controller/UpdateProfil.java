/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.Controller;
import mastermind.View.*;

/**
 *
 * @author ferre
 */
public class UpdateProfil extends AbstractController{
    @Override
    public void perform() {
        this.setView(new UpdateprofilView());
    }
}
