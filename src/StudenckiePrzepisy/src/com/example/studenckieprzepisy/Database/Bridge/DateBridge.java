package com.example.studenckieprzepisy.Database.Bridge;

import com.example.studenckieprzepisy.AddRecipe.PrzepisSkladnikWybor;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Kategoria;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;
import com.example.studenckieprzepisy.Database.DatabaseObjects.PrzepisSkladnik;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Skladnik;
import com.example.studenckieprzepisy.Database.Factory.DatabaseFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public abstract class DateBridge { //tylko taka "odmiana" mostu ma moim zdaniem sens
    protected DatabaseFactory factory;

    public DateBridge(DatabaseFactory factory) {
        this.factory = factory;
    }

    public void setFactory(DatabaseFactory factory) {
        this.factory = factory;
    }

    public abstract void addManyPrzepisSkladnik(List<PrzepisSkladnik> ps);

    public abstract void addPrzepisSkladnik(PrzepisSkladnik ps);

    public abstract void addManySkladnik(List<Skladnik> s);

    public abstract void addSkladnik(Skladnik s);

    public abstract void addPrzepis(int id_Kategorii, String nazwa, String opis, String zdjecie, ArrayList<PrzepisSkladnikWybor> ps);

    public abstract void insertManyPrzepis(List<Przepis> p);

    public abstract void insertPrzepis(Przepis p);

    public abstract void insertManyKategorie(List<Kategoria> k);

    public abstract void insertKategoria(Kategoria k);

    public abstract List<Kategoria> getKategorie();

    public abstract List<Przepis> getPrzepisy();

    public abstract Przepis getPrzepis(String nazwa);

    public abstract List<Przepis> getPrzepisByKategoriaId(int id);

    public abstract List<String> getMiara();

    public abstract List<Skladnik> getSkladniki();

    public abstract List<PrzepisSkladnik> getPrzepisSkladnik(int idPrzepis);

    public abstract void removePrzepis(Przepis p);

    public abstract List<Przepis> searchPrzepis(String nazwa);

    public abstract List<String> advanceSearch(List<Skladnik> s);

    public abstract DatabaseFactory getFactory();

}
