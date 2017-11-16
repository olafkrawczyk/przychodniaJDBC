/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwrlab.przychodniajdbc.repository;

import com.pwrlab.przychodniajdbc.DBConnection;
import com.pwrlab.przychodniajdbc.domain.Gabinet;
import com.pwrlab.przychodniajdbc.domain.Lekarz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.pwrlab.przychodniajdbc.domain.Pacjent;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Olaf
 */
public class LekarzeRepository {

    private static DBConnection dbHandler = DBConnection.getInstance();
    private Connection connection;
    private GabinetRepository gabinetRepo = new GabinetRepository();

    public LekarzeRepository() {
        this.connection = DBConnection.getConnection();
    }

    public boolean insertLekarz(String imie, String nazwisko, int limitPrzyjec, String godzina_start, String godzina_koniec, int id_gabinet) {
        try {
            PreparedStatement uprs = connection
                    .prepareStatement("INSERT INTO lekarz (imie,nazwisko,gabinet,max_wizyt,godzina_start,godzina_koniec)"
                            + " VALUES (?,?,?,?,?,?)");
            uprs.setString(1, imie);
            uprs.setString(2, nazwisko);
            uprs.setInt(3, id_gabinet);
            uprs.setInt(4, limitPrzyjec);
            uprs.setString(5, godzina_start);
            uprs.setString(6, godzina_koniec);
            uprs.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(LekarzeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Lekarz> selectAll() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM lekarz");
            LinkedList<Lekarz> result = new LinkedList<>();
            while (rs.next()) {
                Lekarz lekarz = new Lekarz();
                lekarz.setId(rs.getInt("id"));
                lekarz.setNazwisko(rs.getString("nazwisko"));
                lekarz.setImie(rs.getString("imie"));
                lekarz.setLimitPrzyjec(rs.getInt("max_wizyt"));
                Gabinet gabinet = gabinetRepo.selectById(rs.getInt("gabinet"));
                lekarz.setGabinet(gabinet);
                lekarz.setGodzina_koniec(rs.getString("godzina_koniec"));
                lekarz.setGodzina_start(rs.getString("godzina_start"));
                result.add(lekarz);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(LekarzeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Lekarz selectById(int id) {
        try {
            Statement stmt = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM lekarz WHERE id=?");
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            Lekarz lekarz = new Lekarz();
            lekarz.setId(rs.getInt("id"));
            lekarz.setNazwisko(rs.getString("nazwisko"));
            lekarz.setImie(rs.getString("imie"));
            lekarz.setLimitPrzyjec(rs.getInt("max_wizyt"));
            Gabinet gabinet = gabinetRepo.selectById(rs.getInt("gabinet"));
            lekarz.setGabinet(gabinet);
            lekarz.setGodzina_koniec(rs.getString("godzina_koniec"));
            lekarz.setGodzina_start(rs.getString("godzina_start"));

            return lekarz;

        } catch (SQLException ex) {
            Logger.getLogger(LekarzeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
