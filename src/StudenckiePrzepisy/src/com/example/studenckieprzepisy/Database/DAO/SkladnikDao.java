package com.example.studenckieprzepisy.Database.DAO;

import com.example.studenckieprzepisy.Database.DatabaseObjects.Skladnik;

import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public interface SkladnikDao {
    public void addManySkladnik(List<Skladnik> s);

    public void addSkladnik(Skladnik s);

    public List<Skladnik> getSkladniki();
}
