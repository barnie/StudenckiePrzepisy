package com.example.studenckieprzepisy.AdvancedSearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import com.example.studenckieprzepisy.Database.DAO.PrzepisDaoSqlImp;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;
import com.example.studenckieprzepisy.R;
import com.example.studenckieprzepisy.RecipeView.Przeepis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by piotr on 22.06.14.
 */
public class NormalSearch extends Activity {
    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;
    private EditText txtSearch;
    private String search;

    ArrayList<String> przepisy = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_search);
        getActionBar().setTitle("");
        mainListView = (ListView) findViewById(R.id.mainListView);
        search = getSearch();
        // Create and populate a List of planet names.
        List<Przepis> przepisyy = new PrzepisDaoSqlImp(this).getPrzepisy();
        for (Przepis p : przepisyy) {
            przepisy.add(p.getNazwa());
        }

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, przepisy);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NormalSearch.this, Przeepis.class);
                intent.putExtra("KEY", przepisy.get(position));
                search = "";
                saveSearch("");
                startActivity(intent);
            }
        });
        mainListView.setAdapter(listAdapter);

        if (search.compareTo("") != 0)
            NormalSearch.this.listAdapter.getFilter().filter(search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_by_ingredients, menu);
        View v = (View) menu.findItem(R.id.search).getActionView();
        txtSearch = (EditText) v.findViewById(R.id.txt_search);
        txtSearch.setText(search);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search = s.toString();
                NormalSearch.this.listAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void saveSearch(String key) {
        SharedPreferences.Editor pref = NormalSearch.this.getSharedPreferences("com.example.studenckieprzepisy", Context.MODE_PRIVATE).edit();
        pref.putString("NORMAL_SEARCH", key);
        Log.d("XX", key);
        pref.commit();
    }

    public String getSearch() {
        SharedPreferences shared = NormalSearch.this.getSharedPreferences("com.example.studenckieprzepisy", Context.MODE_PRIVATE);
        return shared.getString("NORMAL_SEARCH","");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveSearch(search);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences.Editor pref = NormalSearch.this.getSharedPreferences("com.example.studenckieprzepisy", Context.MODE_PRIVATE).edit();
        pref.putString("NORMAL_SEARCH","");
        search = "";

    }
}