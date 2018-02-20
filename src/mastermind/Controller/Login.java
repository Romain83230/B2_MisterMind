package mastermind.Controller;
import mastermind.View.*;

/**
 *
 * @author ferre
 */
public class Login extends AbstractController{
    
    /**
    * Constructeur manipulant les informations de la classe parente grâce à la méthode super().
    * @param nom le nom de l'utilisateur.
    * @param auth bolléen, true si l'utilisateur est authentifié, sinon false.
    */
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
