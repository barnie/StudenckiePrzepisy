package com.example.studenckieprzepisy.Database.DAO;

import android.content.Context;
import com.example.studenckieprzepisy.Database.Bridge.DateBridge;
import com.example.studenckieprzepisy.Database.Bridge.SqlBridge;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Skladnik;
import com.example.studenckieprzepisy.Database.Factory.Database;

import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public class SkladnikDaoSqlImpl implements SkladnikDao {
    private SkladnikDaoSqlImpl() {
    }

    private Context context;
    private DateBridge sql;

    public SkladnikDaoSqlImpl(Context context) {
        this.context = context;
        this.sql = new SqlBridge(new Database(context, null, null, 1));
    }

    @Override
    public void addManySkladnik(List<Skladnik> s) {
        sql.addManySkladnik(s);
    }

    @Override
    public void addSkladnik(Skladnik s) {
        sql.addSkladnik(s);
    }

    @Override
    public List<Skladnik> getSkladniki() {
        return sql.getSkladniki();
    }
}
