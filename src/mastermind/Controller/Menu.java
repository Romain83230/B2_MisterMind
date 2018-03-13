package mastermind.Controller;
import mastermind.View.*;

/**
 * Classe lancée par défaut au lancement du programme, gère la décision de l'utilisateur stocke l'information pour l'appel depuis la méthode main.
 * @author ferre
 */
public class Menu extends AbstractController{

    /**
    * Constructeur manipulant les informations de la classe parente grâce à la méthode super().
    * @param nom le nom de l'utilisateur.
    * @param auth bolléen, true si l'utilisateur est authentifié, sinon false.
    * @param passwd Le mot de passe MySQL / MariaDB
    */
    public Menu(String nom, boolean auth, String passwd) {
        super(nom, auth, passwd);
    }
    
    /**
     * Lit la décision du joueur, et en fonction de si l'utilisateur est connecté ou non, fixe la valeur de decision sur le choix correspondant.
     */
    @Override
    public void perform() {
        this.setView(new MenuView(this));
        String choix = this.input.nextLine();
        if(this.islogged()) {
            this.decision = choix;
            if(decision.equals("7") || decision.equals("8")) {
                this.decision = "0";
            }
        } else {
            if("1".equals(choix)) {
                this.decision = "7";
            } else if("2".equals(choix)) {
                this.decision = "8";
            } else if("3".equals(choix)) {
                this.decision = "6";
            } else {
                this.decision = "0";
            }
        }
    }
}