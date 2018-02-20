package mastermind.Controller;
import mastermind.View.*;

/**
 * Classe lancée par défaut au lancement du programme, gère la décision de l'utilisateur stocke l'information pour l'appel depuis la méthode main.
 * @author ferre
 */
public class Menu extends AbstractController{

    public Menu(String nom, boolean auth) {
        super(nom, auth);
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