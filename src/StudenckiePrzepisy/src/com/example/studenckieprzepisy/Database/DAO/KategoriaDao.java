package com.example.studenckieprzepisy.Database.DAO;

import com.example.studenckieprzepisy.Database.DatabaseObjects.Kategoria;

import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public interface KategoriaDao {
    public void insertManyKategorie(List<Kategoria> k);

    public void insertKategoria(Kategoria k);

    public List<Kategoria> getKategorie();
}
