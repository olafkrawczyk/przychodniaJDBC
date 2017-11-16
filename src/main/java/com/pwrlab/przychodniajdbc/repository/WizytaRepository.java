/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwrlab.przychodniajdbc.repository;

import com.pwrlab.przychodniajdbc.DBConnection;
import com.pwrlab.przychodniajdbc.domain.Lekarz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.pwrlab.przychodniajdbc.domain.Pacjent;
import com.pwrlab.przychodniajdbc.domain.Wizyta;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Olaf
 */
public class WizytaRepository {

    private static DBConnection dbHandler = DBConnection.getInstance();
    private Connection connection;
    private PacjentRepository pacjentRepo = new PacjentRepository();
    private LekarzeRepository lekarzeRepo = new LekarzeRepository();
    private GabinetRepository gabinetRepo = new GabinetRepository();

    public WizytaRepository() {
        this.connection = DBConnection.getConnection();
    }

    public boolean insertWizyta(Pacjent pacjent, Lekarz lekarz, Date data) {
        try {
            PreparedStatement uprs = connection
                    .prepareStatement("INSERT INTO wizyta (id_pacjent,id_lekarz,data) VALUES (?,?,?)");
            uprs.setInt(1, pacjent.getId());
            uprs.setInt(2, lekarz.getId());
            uprs.setDate(3, (java.sql.Date) data);
            uprs.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(WizytaRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Wizyta> selectAll() {
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM wizyta");
            LinkedList<Wizyta> result = new LinkedList<>();
            while (rs.next()) {
                Wizyta wizyta = new Wizyta();
                wizyta.setId(rs.getInt("id"));
                wizyta.setPacjent(pacjentRepo.selectById(rs.getInt("id_pacjent")));
                wizyta.setLekarz(lekarzeRepo.selectById(rs.getInt("id_lekarz")));
                wizyta.setData(rs.getDate("data"));
                wizyta.setPacjentPrzyszedl(rs.getBoolean("przyszedl"));
                result.add(wizyta);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(WizytaRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Wizyta selectById(int id) {
        try {
            Statement stmt = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM wizyta WHERE id=?");
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            Wizyta wizyta = new Wizyta();
            wizyta.setId(rs.getInt("id"));
            wizyta.setPacjent(pacjentRepo.selectById(rs.getInt("id_pacjent")));
            wizyta.setLekarz(lekarzeRepo.selectById(rs.getInt("id_lekarz")));
            wizyta.setData(rs.getDate("data"));
            wizyta.setPacjentPrzyszedl(rs.getBoolean("przyszedl"));

            return wizyta;

        } catch (SQLException ex) {
            Logger.getLogger(WizytaRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean setPacjentPrzyszedl(Wizyta wizyta, boolean wartosc) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE wizyta SET przyszedl = ? WHERE id = ?");

            pstmt.setBoolean(1, wartosc);
            pstmt.setInt(2, wizyta.getId());

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
