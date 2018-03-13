package mastermind.Controller;
import java.util.ArrayList;
import mastermind.View.*;

/**
 *
 * @author ferre
 */
public class GetStats extends AbstractController{

    /**
     * Constructeur manipulant les informations de la classe parente grâce à la méthode super().
     * @param nom le nom de l'utilisateur.
     * @param auth bolléen, true si l'utilisateur est authentifié, sinon false.
     * @param passwd Le mot de passe MySQL / MariaDB
     */
    public GetStats(String nom, boolean auth, String passwd) {
        super(nom, auth, passwd);
    }
    /**
     * Affiche les différentes informations concernant les statistiques du joueur
     */
    @Override
    public void perform() {
        this.setView(new StatView(this));
        this.getView().send("Nombre de parties jouées : " + Database.Select("parties_jouees", "statistiques", this.getName()));
        this.getView().send("Parties Gagnées : " + Database.Select("parties_win", "statistiques",this.getName()));
        this.getView().send("Parties perdues : " + Database.Select("parties_lose", "statistiques", this.getName()));
        this.getView().send("Résultat meilleure partie : " + Database.Select("resultat_meilleure_partie", "statistiques", this.getName())+ " Date : " + Database.Select("date_meilleure_partie", "statistiques", this.getName()) );
        this.getView().send("Place dans le classement : " + GenerateClassement());
    }
    /**
     * 
     * @return une chaîne de caractère comportant la place du joueur actuel dans le classement
     */
    private String GenerateClassement(){
        String place="";
       ArrayList classement = Database.SelectClassement();
       for(int i=0; i<classement.size();i++){
           if(classement.get(i).toString().equals(this.getName())){
           place += (i+1);
           }
       }
        place+= " / " + classement.size();
        return place;
    }
}
