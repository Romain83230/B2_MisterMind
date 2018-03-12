package mastermind.Controller;
import mastermind.View.*;

/**
 *
 * @author ferre
 */
public class GetProfil extends AbstractController{

    /**
     * Constructeur manipulant les informations de la classe parente grâce à la méthode super().
     * @param nom le nom de l'utilisateur.
     * @param auth bolléen, true si l'utilisateur est authentifié, sinon false.
     */
    public GetProfil(String nom, boolean auth) {
        super(nom, auth);
    }
    /**
     * Affiche les différentes informations du profil
     */
    @Override
    
    public void perform() {
        this.setView(new ProfilView(this));
        this.getView().send("Login : " + Database.Select("login", "joueur", this.getName()));
        this.getView().send("Nom : " + Database.Select("nom", "joueur",this.getName()));
        this.getView().send("Prénom : " + Database.Select("prenom", "joueur", this.getName()));
        this.getView().send("Email : " + Database.Select("email", "joueur", this.getName()));
        this.getView().send("Date de naissance : " + Database.Select("date_naissance", "joueur", this.getName()));
        
    }
    /**
     * 
     * @return l'email du joueur 
     */
    public String getMail(){
        return Database.Select("email", "joueur", this.getName());
    }
}
