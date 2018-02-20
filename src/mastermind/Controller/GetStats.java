package mastermind.Controller;
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
     */
    public GetStats(String nom, boolean auth) {
        super(nom, auth);
    }
    @Override
    public void perform() {
        this.setView(new StatView(this));
    }
}
