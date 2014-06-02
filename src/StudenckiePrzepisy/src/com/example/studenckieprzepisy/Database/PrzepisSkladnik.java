package com.example.studenckieprzepisy.Database;

/**
 * Created by piotr on 21.04.14.
 */
public class PrzepisSkladnik {
    private int idprzepis;
    private int idskladnik;
    private String miara;
    private String ile;

    public PrzepisSkladnik(int idprzepis, int idskladnik, String miara, String ile) {
        this.idprzepis = idprzepis;
        this.idskladnik = idskladnik;
        this.miara = miara;
        this.ile = ile;
    }

    @Override
    public String toString() {
        return "" + getIdprzepis() + ":" + getIdskladnik() + ":" + getIle() + ":" + getMiara();
    }

    public int getIdprzepis() {
        return idprzepis;
    }

    public void setIdprzepis(int idprzepis) {
        this.idprzepis = idprzepis;
    }

    public int getIdskladnik() {
        return idskladnik;
    }

    public void setIdskladnik(int idskladnik) {
        this.idskladnik = idskladnik;
    }

    public String getMiara() {
        return miara;
    }

    public void setMiara(String miara) {
        this.miara = miara;
    }

    public String getIle() {
        return ile;
    }

    public void setIle(String ile) {
        this.ile = ile;
    }
}
