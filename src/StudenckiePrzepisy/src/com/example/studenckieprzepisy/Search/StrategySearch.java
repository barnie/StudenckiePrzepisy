package com.example.studenckieprzepisy.Search;

import android.content.Context;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Skladnik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 07.06.14.
 */
public class StrategySearch {

    private Context context = null;
    private String nazwa = null;
    private List<Skladnik> przepis = null;

    public StrategySearch(Context context, String nazwa) {
        this.context = context;
        this.nazwa = nazwa;
    }

    public StrategySearch(Context context, List<Skladnik> przepisy) {
        this.context = context;
        this.przepis = przepisy;
    }

    public List<Przepis> search() {
        if (nazwa != null)
            return new SimpleSearch(nazwa, context).search();
        else if (przepis != null)
            return new AdvancedSearch(przepis, context).search();
        return new ArrayList<Przepis>(); //better to return empty list ;)
    }

}
