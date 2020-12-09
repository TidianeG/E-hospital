package com.enginer.e_hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccessBase {
    private static String dbUrl = "jdbc:mysql://192.168.43.135:3306/e_hospital";
    private static String dbUsername = "gaye95cheikh";
    private static String dbPassword = "27ndawGAYE";

    public Boolean testLogin(String login, String password){
        Connection co = null;
        Boolean logged=false;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("chargement du pilote réussi");
            co = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            ResultSet resultats = null;
            String requete = "SELECT * FROM users WHERE email="+login+" AND password="+password;
            Statement st = co.createStatement();
            resultats = st.executeQuery(requete);
            if (resultats.getRow()!=0){
                logged=true;
            }
            else {
                logged=false;
            }
        }
        catch (Exception se) {
            System.out.println("connexion impossible");
        }
        return logged;
    }
}
