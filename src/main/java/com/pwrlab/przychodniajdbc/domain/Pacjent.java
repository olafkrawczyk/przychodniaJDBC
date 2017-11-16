/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwrlab.przychodniajdbc.domain;

/**
 *
 * @author Olaf
 */
public class Pacjent {
    
    private int id;
    private String imie;
    private String nazwisko;
    private String PESEL;
    
    public Pacjent() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }
    
    

    @Override
    public String toString() {
        return id + " " + imie + " " + nazwisko + " " + PESEL;
    }
    
}
