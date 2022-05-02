package com.nam.model;
import java.io.*;
public class country implements Serializable{
    private String tenquocgia;
    private String quocky;
    private String danso;
    private String dientich;
    private String thudo;
    private String map;

    public country() {
    }

    public country(String tenquocgia, String quocky, String danso, String dientich, String thudo, String map) {
        this.tenquocgia = tenquocgia;
        this.quocky = quocky;
        this.danso = danso;
        this.dientich = dientich;
        this.thudo = thudo;
        this.map = map;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getDanso() {
        return danso;
    }

    public void setDanso(String danso) {
        this.danso = danso;
    }

    public String getDientich() {
        return dientich;
    }

    public void setDientich(String dientich) {
        this.dientich = dientich;
    }

    public String getThudo() {
        return thudo;
    }

    public void setThudo(String thudo) {
        this.thudo = thudo;
    }

    public String getTenquocgia() {
        return tenquocgia;
    }

    public void setTenquocgia(String tenquocgia) {
        this.tenquocgia = tenquocgia;
    }

    public String getQuocky() {
        return quocky;
    }

    public void setQuocky(String quocky) {
        this.quocky = quocky;
    }
}
