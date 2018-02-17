/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.Controller;

import java.util.Scanner;
import mastermind.Model.DBConnection;
import mastermind.View.AbstractView;

/**
 *
 * @author ferre
 */
public abstract class AbstractController {
    public String decision;
    private AbstractView View;
    private DBConnection Database;
    private boolean logged;
    private String session;
    public Scanner input;
    
    public AbstractController(String nom, boolean auth) {
        this.logged = auth;
        this.session = nom;
        this.View = null;
        this.Database = new DBConnection();
        this.input = new Scanner(System.in);
        this.perform();
    }
    
    public void setView(AbstractView Vue) {
        this.View = Vue;
    }
    
    public AbstractView getView() {
        return this.View;
    }
    
    public boolean islogged() {
        return this.logged;
    }
    
    public String getName(){
        return this.session;
    }
    
    public void setSession(String nom) {
        this.session = nom;
        this.logged = true;
    }
    
    abstract public void perform();
}
