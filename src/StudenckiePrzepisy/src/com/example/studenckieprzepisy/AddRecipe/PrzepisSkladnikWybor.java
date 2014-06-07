package com.example.studenckieprzepisy.AddRecipe;

import com.example.studenckieprzepisy.Database.DatabaseObjects.Skladnik;

/**
 * Created by piotr on 29.05.14.
 */
public class PrzepisSkladnikWybor {

    public boolean isChoosen() {
        return choosen;
    }

    public void setChoosen(boolean choosen) {
        this.choosen = choosen;
    }

    public Skladnik getSkladnik() {
        return skladnik;
    }

    public void setSkladnik(Skladnik skladnik) {
        this.skladnik = skladnik;
    }

    public String getMiara() {
        return miara;
    }

    public void setMiara(String miara) {
        this.miara = miara;
    }

    public float getIle() {
        return ile;
    }

    public void setIle(float ile) {
        this.ile = ile;
    }

    @Override
    public String toString() {
        return getSkladnik().toString() + " : " + getIle() + " " + getMiara() + "!" + choosen;
    }

    public Skladnik skladnik;
    public boolean choosen;
    public String miara;
    public float ile;

    public PrzepisSkladnikWybor(Skladnik skladnik) {
        this.skladnik = skladnik;
        this.choosen = false;
        this.miara = "";
        this.ile = 0.0f;
    }

    public PrzepisSkladnikWybor(Skladnik skladnik, boolean choosen) {
        this.skladnik = skladnik;
        this.choosen = choosen;
        this.miara = "";
        this.ile = 0.0f;
    }
}
