package com.example.studenckieprzepisy.Database;

/**
 * Created by piotr on 21.04.14.
 */
public class Skladnik {
    private int id;
    private String nazwa;
    private int ile;

    public Skladnik(int id, String nazwa, int ile) {
        this.id = id;
        this.nazwa = nazwa;
        this.ile = ile;
    }

    public Skladnik(String nazwa, int ile) {
        this.id = 0;
        this.nazwa = nazwa;
        this.ile = ile;
    }

    public Skladnik(String nazwa) {
        this.id = 0;
        this.nazwa = nazwa;
        this.ile = 1;
    }

    @Override
    public String toString() {
        return "" + getId() + ":" + getNazwa() + ":" + getIle();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getIle() {
        return ile;
    }

    public void setIle(int ile) {
        this.ile = ile;
    }
}
