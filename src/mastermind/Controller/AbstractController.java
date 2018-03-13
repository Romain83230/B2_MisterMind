package mastermind.Controller;

import java.util.Scanner;
import mastermind.Model.DBConnection;
import mastermind.View.AbstractView;

/**
 * Classe abstraite des contrôleurs, contient des méthodes utiles à l'interaction des contrôleurs avec les vues, ainsi que toutes les entrées utilisateur.
 * @author ferre
 */
public abstract class AbstractController {
    public String decision;
    private AbstractView View;
    protected DBConnection Database;
    private boolean logged;
    private String session;
    public Scanner input;
    
    /**
     * Initie les attributs de l'objet à leurs valeurs de base.
     * @param nom le nom de l'utilisateur.
     * @param auth bolléen, true si l'utilisateur est authentifié, sinon false.
     */
    public AbstractController(String nom, boolean auth) {
        this.logged = auth;
        this.session = nom;
        this.View = null;
        this.Database = new DBConnection();
        this.input = new Scanner(System.in);
        this.perform();
    }
    
    /**
     * Attibue la vue à l'objet AbstractController.
     * @param Vue La vue à attribuer à l'objet
     */
    public void setView(AbstractView Vue) {
        this.View = Vue;
    }
    
    /**
     * Utilisée depuis les enfants de cet objet pour récupérer la vue utilisée.
     * @return La vue attribuée à l'objet
     */
    public AbstractView getView() {
        return this.View;
    }
    
    /**
     * Permet savoir si oui ou non l'utilisateur est connecté.
     * @return True si l'utilisateur est connecté, False s'il ne l'est pas
     */
    public boolean islogged() {
        return this.logged;
    }
    
    /**
     * Permet de connaître le nom de l'utilisateur.
     * @return Le nom de l'utilisateur
     */
    public String getName(){
        return this.session;
    }
    
    /**
     * Attribue un nom à l'utilisateur et passe l'authentification à True.
     * @param nom Le nom à attribuer
     */
    public void setSession(String nom) {
        this.session = nom;
        this.logged = true;
    }
    
    /**
     * Méthode abstraite appellée après la création de la vue, à définir dans chaque classe héritée.
     */
    abstract public void perform();
}