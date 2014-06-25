package com.example.studenckieprzepisy.Search;

import android.content.Context;
import com.example.studenckieprzepisy.Database.Bridge.DateBridge;
import com.example.studenckieprzepisy.Database.Bridge.SqlBridge;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;
import com.example.studenckieprzepisy.Database.Factory.Database;

import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public class SimpleSearch implements Search {

    private String nazwa_przepisu;
    private Context context;

    public SimpleSearch(String nazwa, Context context) {
        nazwa_przepisu = nazwa;
        this.context = context;
    }

    @Override
    public List<Przepis> search() {
        DateBridge db = new SqlBridge(new Database(context, null, null, 1));
        return db.searchPrzepis(nazwa_przepisu.trim());
    }
}
