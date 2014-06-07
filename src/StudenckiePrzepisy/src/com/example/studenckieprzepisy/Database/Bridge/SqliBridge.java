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
public class SqliBridge extends DateBridge {

    public SqliBridge(DatabaseFactory factory){
        super(factory);
    }

    @Override
    public void addManyPrzepisSkladnik(List<PrzepisSkladnik> ps) {
        this.factory.addManyPrzepisSkladnik(ps);
    }

    @Override
    public void addPrzepisSkladnik(PrzepisSkladnik ps) {
        this.factory.addPrzepisSkladnik(ps);
    }

    @Override
    public void addManySkladnik(List<Skladnik> s) {
        this.factory.addManySkladnik(s);
    }

    @Override
    public void addSkladnik(Skladnik s) {
        this.factory.addSkladnik(s);
    }

    @Override
    public void addPrzepis(int id_Kategorii, String nazwa, String opis, String zdjecie, ArrayList<PrzepisSkladnikWybor> ps) {
        this.factory.addPrzepis(id_Kategorii,nazwa, opis, zdjecie, ps);
    }

    @Override
    public void insertManyPrzepis(List<Przepis> p) {
        this.factory.insertManyPrzepis(p);
    }

    @Override
    public void insertPrzepis(Przepis p) {
        this.factory.insertPrzepis(p);
    }

    @Override
    public void insertManyKategorie(List<Kategoria> k) {
        this.factory.insertManyKategorie(k);
    }

    @Override
    public void insertKategoria(Kategoria k) {
        this.factory.insertKategoria(k);
    }

    @Override
    public List<Kategoria> getKategorie() {
        return this.factory.getKategorie();
    }

    @Override
    public List<Przepis> getPrzepisy() {
        return this.factory.getPrzepisy();
    }

    @Override
    public Przepis getPrzepis(String nazwa) {
        return this.factory.getPrzepis(nazwa);
    }

    @Override
    public List<Przepis> getPrzepisByKategoriaId(int id) {
        return this.factory.getPrzepisByKategoriaId(id);
    }

    @Override
    public List<String> getMiara() {
        return this.factory.getMiara();
    }

    @Override
    public List<Skladnik> getSkladniki() {
        return this.factory.getSkladniki();
    }

    @Override
    public List<PrzepisSkladnik> getPrzepisSkladnik(int idPrzepis) {
        return this.factory.getPrzepisSkladnik(idPrzepis);
    }

    @Override
    public void removePrzepis(Przepis p) {
        this.factory.removePrzepis(p);
    }

    @Override
    public List<Przepis> searchPrzepis(String nazwa) {
        return this.factory.searchPrzepis(nazwa);
    }

    @Override
    public List<String> advanceSearch(List<Skladnik> s) {
        return this.factory.advanceSearch(s);
    }

    @Override
    public DatabaseFactory getFactory() {
        return this.factory;
    }


}
