/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pwrlab.przychodniajdbc.domain;

import java.util.Date;

/**
 *
 * @author Olaf
 */
public class Wizyta {
    
    public static int CZAS_TRWANIA_WIZYTY = 30;
    
    private int id;
    private Lekarz lekarz;
    private Pacjent pacjent;
    private Date data;
    private int gabinetId;
    private boolean pacjentPrzyszedl;

    public Wizyta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getGabinetId() {
        return gabinetId;
    }

    public void setGabinetId(int gabinetId) {
        this.gabinetId = gabinetId;
    }

    public boolean isPacjentPrzyszedl() {
        return pacjentPrzyszedl;
    }

    public void setPacjentPrzyszedl(boolean pacjentPrzyszedl) {
        this.pacjentPrzyszedl = pacjentPrzyszedl;
    }
    
    
    
}
