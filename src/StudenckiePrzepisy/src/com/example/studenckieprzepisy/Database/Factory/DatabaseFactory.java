package com.example.studenckieprzepisy.Database.Factory;

import com.example.studenckieprzepisy.AddRecipe.PrzepisSkladnikWybor;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Kategoria;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;
import com.example.studenckieprzepisy.Database.DatabaseObjects.PrzepisSkladnik;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Skladnik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public interface DatabaseFactory {
    public enum ConnectionType{

    }
    //Simple factory in future we can easily swap to server version :)

    public void addManyPrzepisSkladnik(List<PrzepisSkladnik> ps);
    public void addPrzepisSkladnik(PrzepisSkladnik ps);
    public void addManySkladnik(List<Skladnik> s);
    public void addSkladnik(Skladnik s);
    public void addPrzepis(int id_Kategorii, String nazwa, String opis, String zdjecie, ArrayList<PrzepisSkladnikWybor> ps);

    public void insertManyPrzepis(List<Przepis> p);
    public void insertPrzepis(Przepis p);
    public void insertManyKategorie(List<Kategoria> k);
    public void insertKategoria(Kategoria k);

    public List<Kategoria> getKategorie();
    public List<Przepis> getPrzepisy();
    public Przepis getPrzepis(String nazwa);
    public List<Przepis> getPrzepisByKategoriaId(int id);
    public List<String> getMiara();
    public List<Skladnik> getSkladniki();
    public List<PrzepisSkladnik> getPrzepisSkladnik(int idPrzepis);

    public void removePrzepis(Przepis p);

    public List<Przepis> searchPrzepis(String nazwa);
    public List<String> advanceSearch(List<Skladnik> s);
}
