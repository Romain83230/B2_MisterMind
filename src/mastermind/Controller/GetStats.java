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
public class GetStats extends AbstractController{

    public GetStats(String nom, boolean auth) {
        super(nom, auth);
    }
    @Override
    public void perform() {
        this.setView(new StatView(this));
        this.getView().send("Parties jouées : " + Database.Select("parties_joues", "statistiques", "login", this.getName()));
        this.getView().send("Parties gagnées : " + Database.Select("parties_win", "statistiques", "login", this.getName()));
        this.getView().send("Parties perdues : " + Database.Select("parties_lose", "statistiques", "login", this.getName()));
        this.getView().send("Meilleure partie : " + Database.Select("resultat_meilleure_partie", "statistiques", "login", this.getName()));
        this.getView().send("Classement : " + Database.Select("classement_joueur", "statistiques", "login", this.getName()));
        
    }
}
