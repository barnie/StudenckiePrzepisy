package com.example.studenckieprzepisy.Database.DAO;

import android.content.Context;
import com.example.studenckieprzepisy.AddRecipe.PrzepisSkladnikWybor;
import com.example.studenckieprzepisy.Database.Bridge.DateBridge;
import com.example.studenckieprzepisy.Database.Bridge.SqlBridge;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Skladnik;
import com.example.studenckieprzepisy.Database.Factory.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public class PrzepisDaoSqlImp implements PrzepisDao {

    private PrzepisDaoSqlImp() {
    }

    private Context context;
    private DateBridge sql;

    public PrzepisDaoSqlImp(Context context) {
        this.context = context;
        this.sql = new SqlBridge(new Database(context, null, null, 1));
    }

    @Override
    public void insertManyPrzepis(List<Przepis> p) {
        sql.insertManyPrzepis(p);
    }

    @Override
    public void insertPrzepis(Przepis p) {
        sql.insertPrzepis(p);
    }

    @Override
    public List<Przepis> getPrzepisy() {
        return sql.getPrzepisy();
    }

    @Override
    public Przepis getPrzepis(String nazwa) {
        return sql.getPrzepis(nazwa);
    }

    @Override
    public List<Przepis> searchPrzepis(String nazwa) {
        return sql.searchPrzepis(nazwa);
    }

    @Override
    public List<Przepis> getPrzepisByKategoriaId(int id) {
        return sql.getPrzepisByKategoriaId(id);
    }

    @Override
    public void addPrzepis(int id_Kategorii, String nazwa, String opis, String zdjecie, ArrayList<PrzepisSkladnikWybor> ps) {
        sql.addPrzepis(id_Kategorii, nazwa, opis, zdjecie, ps);
    }

    @Override
    public void removePrzepis(Przepis p) {
        sql.removePrzepis(p);
    }

    @Override
    public List<Przepis> advanceSearch(List<Skladnik> s) {
        return sql.advanceSearch(s);
    }
}
