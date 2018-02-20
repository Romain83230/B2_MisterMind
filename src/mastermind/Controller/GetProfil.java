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
public class GetProfil extends AbstractController{

    public GetProfil(String nom, boolean auth) {
        super(nom, auth);
    }
    @Override
    public void perform() {
        this.setView(new ProfilView(this));
        this.getView().send("Login : " + Database.Select("login", "joueur", "login", this.getName()));
        this.getView().send("Nom : " + Database.Select("nom", "joueur", "login", this.getName()));
        this.getView().send("Prénom : " + Database.Select("prenom", "joueur", "login", this.getName()));
        this.getView().send("Email : " + Database.Select("email", "joueur", "login", this.getName()));
        this.getView().send("Date de naissance : " + Database.Select("date_naissance", "joueur", "login", this.getName()));
        
    }
    
    public String getMail(){
        return Database.Select("email", "joueur", "login", this.getName());
    }
}
