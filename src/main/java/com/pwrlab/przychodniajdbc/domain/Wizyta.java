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
    
    private int id;
    private Lekarz lekarz;
    private Pacjent pacjent;
    private Date data;
    private int gabinetId;
    private boolean pacjentPrzyszedl;
    
}
