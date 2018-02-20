package mastermind.Controller;
import mastermind.View.*;

/**
 * Cette classe gère la fonctionalité 'mise à jour du profil'.
 * @author ferre
 */
public class UpdateProfil extends AbstractController{

    /**
    * Constructeur manipulant les informations de la classe parente grâce à la méthode super().
    * @param nom le nom de l'utilisateur.
    * @param auth bolléen, true si l'utilisateur est authentifié, sinon false.
    */
    public UpdateProfil(String nom, boolean auth) {
        super(nom, auth);
    }
    
    /**
     * Appelle un objet 'afficher le profil' dans un premier temps, puis propose à l'utilisateur de modifier ses informations.
     */
    @Override
    public void perform() {
        this.setView(new UpdateprofilView(this));

        GetProfil profil = new GetProfil(this.getName(),this.islogged());
        
        this.getView().send("Entrez vos nouvelles informations");

        this.getView().send("Nouveau mot de passe:");
        String password = this.input.nextLine();

        this.getView().send("Nouvelle adresse mail ["+ profil.getMail() + "]:");
        String mail = this.input.nextLine();
        if(mail.equals("")){
           mail = profil.getMail();
        }
        
        this.Database.UpdateProfil(this.getName(), mail, password);
    }
}
