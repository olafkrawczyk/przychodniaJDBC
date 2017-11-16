/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwrlab.przychodniajdbc.repository;

import com.pwrlab.przychodniajdbc.DBConnection;
import com.pwrlab.przychodniajdbc.domain.Gabinet;
import com.pwrlab.przychodniajdbc.domain.Pacjent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olaf
 */
public class GabinetRepository {
    
    private static DBConnection dbHandler = DBConnection.getInstance();
    private Connection connection;

    public GabinetRepository() {
        this.connection = DBConnection.getConnection();
    }
    
    public boolean insertGabinet(String pokoj) {
        try {
            PreparedStatement uprs = connection.prepareStatement("INSERT INTO gabinet (nr_pokoju) VALUES (?)");
            uprs.setString(1, pokoj);
            uprs.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(PacjentRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Gabinet> selectAll() {
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM gabinet");
            LinkedList<Gabinet> result = new LinkedList<>();
            while (rs.next()) {
                Gabinet gabinet = new Gabinet();
                gabinet.setId(rs.getInt("id"));
                gabinet.setNumerPokoju(rs.getString("nr_pokoju"));
                result.add(gabinet);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(PacjentRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Gabinet selectById(int id) {
        try {
            Statement stmt = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM gabinet WHERE id=?");
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            
            Gabinet gabinet = new Gabinet();
            gabinet.setId(rs.getInt("id"));
            gabinet.setNumerPokoju(rs.getString("nr_pokoju"));
            
            return gabinet;

        } catch (SQLException ex) {
            Logger.getLogger(PacjentRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}
