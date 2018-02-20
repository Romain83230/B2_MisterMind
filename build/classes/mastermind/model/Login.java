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
public class Login extends AbstractController{

    public Login(String nom, boolean auth) {
        super(nom, auth);
    }
    @Override
    public void perform() {
        this.setView(new LoginView(this));
        
        String login = this.input.nextLine();
        this.getView().send("Veuillez saisir votre mot de passe :");
        String password = this.input.nextLine();
        if(!this.Database.VerifConnection(login, password)){
            /*this.getView().send("Veuillez saisir votre login :");
            login = this.input.nextLine();
            this.getView().send("Veuillez saisir votre mot de passe :");
            password = this.input.nextLine();*/
        }
        else{
            this.setSession(login);
        }
        
    }
}
