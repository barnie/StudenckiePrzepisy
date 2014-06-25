package com.example.studenckieprzepisy.Database.DAO;

import com.example.studenckieprzepisy.AddRecipe.PrzepisSkladnikWybor;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Skladnik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public interface PrzepisDao {
    public void insertManyPrzepis(List<Przepis> p);

    public void insertPrzepis(Przepis p);

    public List<Przepis> getPrzepisy();

    public Przepis getPrzepis(String nazwa);

    public List<Przepis> searchPrzepis(String nazwa);

    public List<Przepis> getPrzepisByKategoriaId(int id);

    public void addPrzepis(int id_Kategorii, String nazwa, String opis, String zdjecie, ArrayList<PrzepisSkladnikWybor> ps);

    public void removePrzepis(Przepis p);

    public List<Przepis> advanceSearch(List<Skladnik> s);
}
