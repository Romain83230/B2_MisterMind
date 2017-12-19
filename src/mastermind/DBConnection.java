/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.*;
import com.mysql.jdbc.Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author LCHAUDESSOLLE
 */
public class DBConnection {
    
    private String url = "jdbc:mysql://localhost/sondages";
    private String login = "root";
    private String password ="";
    private com.mysql.jdbc.Connection  connec = null;
    private com.mysql.jdbc.Statement  st = null;
    private ResultSet  rs = null;
    
    public void Initialize()
    {
        connec = null;
        try {
            connec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mastermind", login, password);
        } catch (SQLException ex) {
            try {
                connec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost", login, password);
                OpenConnection();
                String sql = "CREATE DATABASE mastermind;";
                st.executeUpdate(sql);
                System.out.println("Base de données créée !");
                CloseConnection();
            } catch (SQLException ex1) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        
        try {
            connec =(Connection) DriverManager.getConnection("jdbc:mysql://localhost/mastermind", login, password);
            System.out.println("Connection à la base de donnée");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }   
        
        
    }
    
    public boolean OpenConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            st = (com.mysql.jdbc.Statement) connec.createStatement();
            System.out.println("Connection ouverte");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean CloseConnection(){
        if(st != null){
            try {
                st.close();
                connec.close();
                System.out.println("Connection fermée");
            } catch (SQLException sqlEx) {
                return false;
            } // ignore

            st = null;        
        }
        return true;
    
    }
    
    public void Select(){
        ResultSetMetaData rsmd = null;
        
            try {
                OpenConnection();
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            
                if (st.execute("SELECT * FROM joueur")) {
                    rs = st.getResultSet();
                    rsmd =  rs.getMetaData();
                    while(rs.next()){
                         System.out.println("Ligne " + rs.getRow() + " : ");
                        for(int i = 1; i< rsmd.getColumnCount()+1; i++){ 
                           System.out.print(rsmd.getColumnName(i) + " : ");
                           System.out.println(rs.getString(i));
                        }
                    }

                }
           // for(int i=0; i< rs.. i++){
                //System.out.println(rsmd);
             //   }
            // Now do something with the ResultSet ....
            }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    CloseConnection();
    }
    
    public boolean VerifConnection(String login, String password){        
            try {
                OpenConnection();
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            
                if (st.execute("SELECT login, password FROM joueur")) {
                    rs = st.getResultSet();
                    while(rs.next()){
                        System.out.println("Ligne " + rs.getRow() + " : ");
                            if(rs.getString(1).equals(login) && rs.getString(2).equals(password)){
                                System.out.println("login/mot de passe correct");
                                CloseConnection();
                                return true;
                            }
                            else{
                                System.out.println("Erreur de mot de login / mot de passe");  
                                return false;
                            }
                    }

                }

            }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return false;
    }
}
