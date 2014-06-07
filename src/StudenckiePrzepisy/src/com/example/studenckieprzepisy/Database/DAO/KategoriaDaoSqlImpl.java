package com.example.studenckieprzepisy.Database.DAO;

import android.content.Context;
import com.example.studenckieprzepisy.Database.Bridge.DateBridge;
import com.example.studenckieprzepisy.Database.Bridge.SqlBridge;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Kategoria;
import com.example.studenckieprzepisy.Database.Factory.Database;

import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public class KategoriaDaoSqlImpl implements KategoriaDao {

    private KategoriaDaoSqlImpl() {
    }

    private Context context;
    private DateBridge sql;

    public KategoriaDaoSqlImpl(Context context) {
        this.context = context;
        this.sql = new SqlBridge(new Database(context, null, null, 1));
    }

    @Override
    public void insertManyKategorie(List<Kategoria> k) {
        sql.insertManyKategorie(k);
    }

    @Override
    public void insertKategoria(Kategoria k) {
        sql.insertKategoria(k);
    }

    @Override
    public List<Kategoria> getKategorie() {
        return sql.getKategorie();
    }
}
