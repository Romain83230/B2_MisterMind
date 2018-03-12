/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind.Model;

import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.*;
import com.mysql.jdbc.Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo
 */

public final class DBConnection {

    private String url = "jdbc:mysql://localhost/mastermind?autoReconnect=true&useSSL=false";
    private String login = "root";
    private String password = "";
    private com.mysql.jdbc.Connection connec = null;
    private com.mysql.jdbc.Statement st = null;
    private ResultSet rs = null;

    public DBConnection() {
        Initialize();
    }

    /**
     * Initialise l'objet DBConnection ( crée la base de donnée si elle n'est
     * pas déjà créée, ainsi que les colonnes correspondantes) Si la base de
     * données est déjà créée, alors se connecter à celle-ci
     */
    public void Initialize() {
        try {
            connec = (Connection) DriverManager.getConnection(url, login, password);
        } catch (SQLException ex) {
            try {
                connec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost?autoReconnect=true&useSSL=false", login, password);
                OpenConnection(connec);
                String sql = "CREATE DATABASE mastermind;";
                st.executeUpdate(sql);
                sql = "USE mastermind;";
                st.executeUpdate(sql);
                sql = "CREATE TABLE joueur\n"
                        + "(\n"
                        + "    login VARCHAR(25) PRIMARY KEY NOT NULL,\n"
                        + "    nom VARCHAR(25) NOT NULL,\n"
                        + "    prenom VARCHAR(25 )NOT NULL,\n"
                        + "    password VARCHAR(25) NOT NULL,\n"
                        + "    email VARCHAR(50) NOT NULL,\n"
                        + "    date_naissance DATE NOT NULL"
                        + ");";
                st.executeUpdate(sql);
                sql = "CREATE TABLE statistiques\n"
                        + "(\n"
                        + "    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n"
                        + "    parties_jouees INT(5) NOT NULL,\n"
                        + "    parties_win INT(3) NOT NULL,\n"
                        + "    parties_lose INT(3) NOT NULL,\n"
                        + "    resultat_meilleure_partie INT(2) NOT NULL,\n"
                        + "    date_meilleure_partie DATE,\n"
                        + "    classement_joueur INT(2) NOT NULL,\n"
                        + "    login VARCHAR(25) NOT NULL,\n"
                        + "     FOREIGN KEY (login) REFERENCES joueur(login)\n"
                        + ")";
                st.executeUpdate(sql);
                CloseConnection();
            } catch (SQLException ex2) {
                System.out.println("SQLException initialize: " + ex2.getMessage());
                System.out.println("SQLState: " + ex2.getSQLState());
                System.out.println("VendorError: " + ex2.getErrorCode());
            }
        }

        try {
            connec = (Connection) DriverManager.getConnection(url, login, password);
            //System.out.println("Connection à la base de donnée");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *Tente d'ouvrir la connexion true si réussi sinon false
     * @return
     */
    public boolean OpenConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connec = (Connection) DriverManager.getConnection(url, login, password);
            st = (com.mysql.jdbc.Statement) connec.createStatement();
            //System.out.println("Connection ouverte");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("SQLException Open: " + ex.getMessage());
            return false;
        }
    }
/**
 * OpenConnection() utilisée pour ouvrir la première connection à la base de données (sinon créer la base)
 * @param co
 * @return 
 */
    public boolean OpenConnection(com.mysql.jdbc.Connection co) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connec = co;
            st = (com.mysql.jdbc.Statement) connec.createStatement();
            //System.out.println("Connection ouverte");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("SQLException Open: " + ex.getMessage());
            return false;
        }
    }
/**
 * Tente de fermer la connection actuelle true si réussi sinon false
 * @return 
 */
    public boolean CloseConnection() {
        if (st != null) {
            try {
                st.close();
                connec.close();
                //System.out.println("Connection fermée");
            } catch (SQLException sqlEx) {
                System.out.println("SQLException Close : " + sqlEx.getMessage());
                return false;
            } // ignore

            st = null;
        }
        return true;

    }
/**
 * 
 * @param adj la colonne à afficher
 * @param table la table à afficher
 * @return une liste de toutes les ligne de la colonne correspondante
 */
    //SELECT SUR TOUTES LES TABLES
    public ArrayList SelectArray(String adj, String table) {
        ResultSetMetaData rsmd = null;
        ArrayList liste = new ArrayList();
        try {
            OpenConnection();
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            if (st.execute("SELECT " + adj + " FROM " + table + ";")) {
                rs = st.getResultSet();
                rsmd = rs.getMetaData();
                while (rs.next()) {
                    //System.out.println("Ligne " + rs.getRow() + " : "); uniquement pour select *
                    for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                        //  System.out.print(rsmd.getColumnName(i) + " : "); uniquement pour select *
                        liste.add(rs.getString(i));
                    }
                }
            }
            // for(int i=0; i< rs.. i++){
            //System.out.println(rsmd);
            //   }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException select: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        CloseConnection();
        return liste;
    }
/**
 * 
 * @param adj colonne à afficher
 * @param table table à afficher
 * @param id nom du joueur
 * @return une seule valeur en sortie, c'est un select précis
 */
    //SELECT PRECIS SUR UN JOUEUR
    public String Select(String adj, String table, String id) {
        ResultSetMetaData rsmd = null;
        String word = "";
        try {
            OpenConnection();
            if (st.execute("SELECT " + adj + " FROM " + table + " WHERE login = '" + id + "';")) {
                rs = st.getResultSet();
                rsmd = rs.getMetaData();
                while (rs.next()) {
                    //System.out.println("Ligne " + rs.getRow() + " : "); uniquement pour select *
                    for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                        //  System.out.print(rsmd.getColumnName(i) + " : "); uniquement pour select *
                        word = rs.getString(i);
                    }
                }
            }
        } catch (SQLException ex) {

            System.out.println("SQLException select: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        CloseConnection();
        return word;
    }
/**
 * 
 * @return une liste des joueurs classés selon leur meilleure partie
 */
    public ArrayList SelectClassement() {
        ResultSetMetaData rsmd = null;
        ArrayList liste = new ArrayList();
        try {
            OpenConnection();
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            if (st.execute("SELECT login FROM statistiques ORDER BY `resultat_meilleure_partie` DESC ;")) {
                rs = st.getResultSet();
                rsmd = rs.getMetaData();
                while (rs.next()) {
                    //System.out.println("Ligne " + rs.getRow() + " : "); uniquement pour select *
                    for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                        //  System.out.print(rsmd.getColumnName(i) + " : "); uniquement pour select *
                        liste.add(rs.getString(i));
                    }
                }
            }
            // for(int i=0; i< rs.. i++){
            //System.out.println(rsmd);
            //   }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException select: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        CloseConnection();
        return liste;
    }
/**
 * Vérifie que l'identifiant et le mot de passe utilisé lors de l'authentification est juste
 * @param login login du joueur
 * @param password mot de passe du joueur
 * @return true si la connection est vérifié false s'il y a une erreur d'identification
 */
    public boolean VerifConnection(String login, String password) {
        try {
            OpenConnection();
            if (st.execute("SELECT login, password FROM joueur")) {
                rs = st.getResultSet();
                while (rs.next()) {
                    //System.out.println("Ligne " + rs.getRow() + " : ");
                    if (rs.getString(1).equals(login) && rs.getString(2).equals(password)) {
                        System.out.println("login/mot de passe correct");
                        CloseConnection();
                        return true;
                    }
                }
                System.out.println("Erreur de mot de login / mot de passe");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException verif: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }
/**
 * Permet  l'inscription d'un joueur dans la base de données
 * 
 * @param login login du joueur
 * @param nom nom du joueur
 * @param prenom prenom du joueur
 * @param email email du joueur
 * @param jour de naissance
 * @param mois mois de naissance
 * @param annee annee de naissance
 * @param password  mot de passe du joueur
 */
    public void Inscrire(String login, String nom, String prenom, String email, int jour, int mois, int annee, String password) {
        try {
            OpenConnection();
            if (st.execute("INSERT INTO joueur VALUES('" + login + "', '" + nom + "', '" + prenom + "', '" + password + "', '" + email + "', '" + annee + "-" + mois + "-" + jour + "')")) {
                System.out.println("Inscription réussie");
            }
            if (st.execute("INSERT INTO statistiques VALUES(id,'0','0','0','0',NULL,'0','" + login + "')")) {
                System.out.println("Inscription réussie");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException Insert: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        CloseConnection();
    }

    /**
     * Permet de modifier les informations du joueur
     * 
     * @param login login du joueur
     * @param email email du joueru
     * @param password mot de passe du joueur
     */
    public void UpdateProfil(String login, String email, String password) {
        try {
            OpenConnection();
            if (st.execute("UPDATE joueur SET password='" + password + "', email='" + email + "' WHERE login='" + login + "';")) {
                System.out.println("Informations mises à jour");
            }
        } catch (SQLException ex) {
        }
        CloseConnection();
    }
/**
 * Modifie les statistiques du joueur après une partie
 * @param nombreCoup nombre de tours du joueur
 * @param gagner true si la partie est gagnée sinon false
 * @param login login du joueur
 * @throws SQLException 
 */
    public void AddStat(int nombreCoup, boolean gagner, String login) throws SQLException {
        int points = 9 - nombreCoup;
        int pointsDepart = Integer.parseInt(Select("resultat_meilleure_partie", "statistiques", login));
        OpenConnection();
        if (gagner) {
            if (points > pointsDepart) {
                if (st.execute("UPDATE statistiques SET parties_jouees=parties_jouees + 1, parties_win=parties_win + 1, date_meilleure_partie = CURDATE(), resultat_meilleure_partie='" + points + "' WHERE login='" + login + "';")) {
                    System.out.println("Informations mises à jour");
                }
            } else {
                if (st.execute("UPDATE statistiques SET parties_jouees=parties_jouees + 1, parties_win=parties_win + 1 WHERE login='" + login + "';")) {
                    System.out.println("Informations mises à jour");
                }
            }
        } else {
            if (st.execute("UPDATE statistiques SET parties_jouees= parties_jouees + 1, parties_lose=parties_lose + 1 WHERE login='" + login + "';")) {
                System.out.println("Informations mises à jour");
            }
        }

        CloseConnection();
    }

}
