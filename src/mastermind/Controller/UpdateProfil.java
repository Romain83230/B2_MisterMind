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

    public UpdateProfil(String nom, boolean auth) {
        super(nom, auth);
    }
    
    @Override
    public void perform() {
        this.setView(new UpdateprofilView(this));

        GetProfil profil = new GetProfil(this.getName(),this.islogged());
        
        this.getView().send("Entrez vos nouvelles informations");

        this.getView().send("Nouveau mot de passe:");
        String password = this.input.nextLine();

        //this.getView().send("Nouvelle adresse mail ["+ profil.getMail() + "]:");
        //String mail = this.input.nextLine();
        //if(mail.equals("")){
        //    mail = profil.getMail();
        //}

        //this.Database.UpdateProfil(this.getName(), mail, password);
    }
}
