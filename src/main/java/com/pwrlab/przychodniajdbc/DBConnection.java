/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwrlab.przychodniajdbc;

import java.sql.*;

/**
 *
 * @author Olaf
 */
public class DBConnection {
    
    private static String DB_URL = "jdbc:sqlite:C:/sqlite/db/przychodnia.db";
    private static DBConnection instance = null;
    private Connection connection = null;
    
    
    
    private DBConnection() {
        try {
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection(DB_URL);
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
    }
    
    public DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
            return instance;
        }
        return instance;
    }
    
    public Connection getConnection() {
        return this.connection;
    }
}
