package com.example.studenckieprzepisy.Database.DAO;

import android.content.Context;
import com.example.studenckieprzepisy.Database.Bridge.DateBridge;
import com.example.studenckieprzepisy.Database.Bridge.SqlBridge;
import com.example.studenckieprzepisy.Database.DatabaseObjects.PrzepisSkladnik;
import com.example.studenckieprzepisy.Database.Factory.Database;

import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public class PrzepisSkladnikDaoSqlImpl implements PrzepisSkladnikDao {
    private PrzepisSkladnikDaoSqlImpl() {
    }

    private Context context;
    private DateBridge sql;

    public PrzepisSkladnikDaoSqlImpl(Context context) {
        this.context = context;
        this.sql = new SqlBridge(new Database(context, null, null, 1));
    }

    @Override
    public void addManyPrzepisSkladnik(List<PrzepisSkladnik> ps) {
        sql.addManyPrzepisSkladnik(ps);
    }

    @Override
    public void addPrzepisSkladnik(PrzepisSkladnik ps) {
        sql.addPrzepisSkladnik(ps);
    }

    @Override
    public List<PrzepisSkladnik> getPrzepisSkladnik(int idPrzepis) {
        return sql.getPrzepisSkladnik(idPrzepis);
    }
}
