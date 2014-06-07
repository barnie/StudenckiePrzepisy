package com.example.studenckieprzepisy.Database.DAO;

import com.example.studenckieprzepisy.Database.DatabaseObjects.PrzepisSkladnik;

import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public interface PrzepisSkladnikDao {

    public void addManyPrzepisSkladnik(List<PrzepisSkladnik> ps);

    public void addPrzepisSkladnik(PrzepisSkladnik ps);

    public List<PrzepisSkladnik> getPrzepisSkladnik(int idPrzepis);

}
