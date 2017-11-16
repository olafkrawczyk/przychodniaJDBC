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
public class Lekarz {

    private int id;
    private String imie;
    private String nazwisko;
    private int limitPrzyjec;
    private Gabinet gabinet;
    private String godzina_start;
    private String godzina_koniec;

    public Lekarz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getLimitPrzyjec() {
        return limitPrzyjec;
    }

    public void setLimitPrzyjec(int limitPrzyjec) {
        this.limitPrzyjec = limitPrzyjec;
    }

    public Gabinet getGabinet() {
        return gabinet;
    }

    public void setGabinet(Gabinet gabinet) {
        this.gabinet = gabinet;
    }

    public String getGodzina_start() {
        return godzina_start;
    }

    public void setGodzina_start(String godzina_start) {
        this.godzina_start = godzina_start;
    }

    public String getGodzina_koniec() {
        return godzina_koniec;
    }

    public void setGodzina_koniec(String godzina_koniec) {
        this.godzina_koniec = godzina_koniec;
    }

    @Override
    public String toString() {
        return id + " " + imie
                + " " + nazwisko
                + " limit " + limitPrzyjec
                + " pok. " + gabinet.getNumerPokoju()
                + " od " + godzina_start 
                + " do " + godzina_koniec ;
    }

}
