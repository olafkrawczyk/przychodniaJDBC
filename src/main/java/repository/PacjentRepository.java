/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import com.pwrlab.przychodniajdbc.DBConnection;
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
public class PacjentRepository {

    private static DBConnection dbHandler = DBConnection.getInstance();
    private Connection connection;

    public PacjentRepository() {
        this.connection = DBConnection.getConnection();
    }

    public boolean insertPacjent(String imie, String nazwisko, String pesel) {
        try {
            PreparedStatement uprs = connection.prepareStatement("INSERT INTO pacjent(imie, nazwisko, pesel) VALUES (?,?,?)");
            uprs.setString(1, imie);
            uprs.setString(2, nazwisko);
            uprs.setString(3, pesel);
            uprs.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(PacjentRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Pacjent> selectAll() {
        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pacjent");
            LinkedList<Pacjent> result = new LinkedList<>();
            while (rs.next()) {
                Pacjent pacjent = new Pacjent();
                pacjent.setId(rs.getInt("id"));
                pacjent.setNazwisko(rs.getString("nazwisko"));
                pacjent.setImie(rs.getString("imie"));
                pacjent.setPESEL(rs.getString("pesel"));
                result.add(pacjent);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(PacjentRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Pacjent selectById(int id) {
        try {
            Statement stmt = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM pacjent WHERE id=?");
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            
            Pacjent pacjent = new Pacjent();
            pacjent.setId(rs.getInt("id"));
            pacjent.setImie(rs.getString("imie"));
            pacjent.setNazwisko(rs.getString("nazwisko"));
            pacjent.setPESEL(rs.getString("pesel"));
            
            return pacjent;

        } catch (SQLException ex) {
            Logger.getLogger(PacjentRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
