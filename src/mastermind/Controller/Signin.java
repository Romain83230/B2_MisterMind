/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mastermind.View.*;

/**
 *
 * @author ferre
 */
public class Signin extends AbstractController{

    public Signin(String nom, boolean auth) {
        super(nom, auth);
    }


    @Override
    public void perform() {
        this.setView(new SigninView(this));
        
        Object[] tableauLogin = Database.SelectArray("login","joueur").toArray();
        //LOGIN
        this.getView().send("Veuillez saisir votre login :");
        String login = this.input.nextLine();
        
        for(int i = 0; i<tableauLogin.length; i++){
            while(login.equals(tableauLogin[i].toString()) || login.length() < 5 || login.length() > 25){
                if(login.equals(tableauLogin[i].toString())){
                this.getView().send(login + " n'est pas disponible, en prendre un autre");
                this.getView().send("Veuillez saisir votre login :");
                login = this.input.nextLine();
                }
                else{
                    this.getView().send(login + " ne contient pas entre 5 et 25 caractères");
                    this.getView().send("Veuillez saisir votre login :");
                    login = this.input.nextLine();
                }
            }
        }

        //NOM
        this.getView().send("Veuillez saisir votre nom :");
        String nom = this.input.nextLine();
        //PRENOM
        this.getView().send("Veuillez saisir votre prenom :");
        String prenom = this.input.nextLine();
        //EMAIL
        this.getView().send("Veuillez saisir votre email :");
        String email = this.input.nextLine();
        while(email.indexOf('@') == -1 || email.indexOf('.') == -1 ){
            this.getView().send("L'adresse ail n'est pas valide.");
            this.getView().send("Veuillez saisir votre email :");
            email = this.input.nextLine();
        }
        //DATE DE NAISSANCE
        this.getView().send("Veuillez saisir votre date de naissance au format JJMMAAAA :");
        /*while (true)
            try {
                String naiss = sc.nextLine();
                while(naiss.length() != 8){
                    naiss = sc.nextLine();
                }
                naissance = Integer.parseInt(naiss);
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Veuillez saisir votre date de naissance au format JJMMAAAA :");
            }*/
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String naissance;
        int annee;
        int mois;
        int jour;
        
        while(true){
            naissance = this.input.nextLine();
            Date d;
            try {
                d = sdf.parse(naissance);
                String t = sdf.format(d);
                if(t.compareTo(naissance) !=  0){
                    this.getView().send("Non valide");
                }
                else{
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    annee = cal.get(Calendar.YEAR);
                    mois = cal.get(Calendar.MONTH);
                    jour = cal.get(Calendar.DAY_OF_MONTH);
                    break;
                }
            } catch (Exception e) {
                    this.getView().send("Non valide");
            }
        }                           
        
        //MOT DE PASSE
        this.getView().send("Veuillez saisir votre mot de passe :");
        String password = this.input.nextLine();
        while(password.length() < 5){
            this.getView().send("Le mot de passse ne contient pas plus de 5 caractères");
                this.getView().send("Veuillez saisir votre mot de passe :");
                password = this.input.nextLine();
        }
        //CONFIRMATION DE MOT DE PASSE
        this.getView().send("Confirmer votre mot de passe :");
        String password2 = this.input.nextLine();
        while(!password2.equals(password)){
            this.getView().send(" Le mot de passe ne correspond pas");
            this.getView().send("Veuillez saisir un nouveu mot de passe :");
            password = this.input.nextLine();
            while(password.length() < 5){
            this.getView().send("Le mot de passse ne contient pas plus de 5 caractères");
                this.getView().send("Veuillez saisir votre mot de passe :");
                password = this.input.nextLine();
            }
            this.getView().send("Confirmer votre mot de passe :");
            password2 = this.input.nextLine();
                
        }
        
        this.getView().send("Inscription en cours....");
        Database.Inscrire(login, nom, prenom, email, jour, mois, annee, password);
    }
}
