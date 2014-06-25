package com.example.studenckieprzepisy.AdvancedSearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.studenckieprzepisy.AddRecipe.PrzepisSkladnikWybor;
import com.example.studenckieprzepisy.Database.Bridge.DateBridge;
import com.example.studenckieprzepisy.Database.Bridge.SqlBridge;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Skladnik;
import com.example.studenckieprzepisy.Database.Factory.Database;
import com.example.studenckieprzepisy.R;
import com.example.studenckieprzepisy.RecipeView.Przeepis;
import com.example.studenckieprzepisy.Search.StrategySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by piotr on 03.06.14.
 */
public class AdvancedSearch extends Activity {

    private Button b;
    private ListView list;
    MyCustomAdapter dataAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addskladniklist);
        b = (Button) findViewById(R.id.button);
        list = (ListView) findViewById(R.id.listView);
        b.setText("Szukaj");
        Database db = new Database(getApplicationContext(), null, null, 1);
        displayListView();
        checkButtonClick();


    }


    private void displayListView() {

        ArrayList<PrzepisSkladnikWybor> stateList = new ArrayList<PrzepisSkladnikWybor>();
        DateBridge db = new SqlBridge(new Database(getApplicationContext(), null, null, 1));
        List<Skladnik> skladniczki = db.getSkladniki();
        Collections.sort(skladniczki, new Comparator<Skladnik>() {
            public int compare(Skladnik a, Skladnik b) {
                return a.getNazwa().compareTo(b.getNazwa());
            }
        });
        Log.d("AAA","AAA");

        SharedPreferences shared = AdvancedSearch.this.getSharedPreferences("com.example.studenckieprzepisy", Context.MODE_PRIVATE);
        for (Skladnik i : skladniczki){
        Log.d("AAA",shared.getString(i.getNazwa(),""));
            if (shared.getString(i.getNazwa(),"").compareTo(i.getNazwa()) == 0)
                stateList.add(new PrzepisSkladnikWybor(i, true));
            else
                stateList.add(new PrzepisSkladnikWybor(i, false));
        }
        dataAdapter = new MyCustomAdapter(this, R.layout.addskladniklist, stateList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                PrzepisSkladnikWybor state = (PrzepisSkladnikWybor) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on : " + state.getMiara(), Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private class MyCustomAdapter extends ArrayAdapter<PrzepisSkladnikWybor> {

        private ArrayList<PrzepisSkladnikWybor> stateList;

        public MyCustomAdapter(Context context, int textViewResourceId,

                               ArrayList<PrzepisSkladnikWybor> stateList) {
            super(context, textViewResourceId, stateList);
            this.stateList = new ArrayList<PrzepisSkladnikWybor>();
            this.stateList.addAll(stateList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {

                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = vi.inflate(R.layout.add_skladnik, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.nazwa);
                holder.name = (CheckBox) convertView
                        .findViewById(R.id.wybor);

                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        final PrzepisSkladnikWybor _state = (PrzepisSkladnikWybor) cb.getTag();
                        _state.setMiara("ZAZNACZONO");
                        _state.setChoosen(cb.isChecked());
                    }
                });

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            PrzepisSkladnikWybor state = stateList.get(position);

            holder.code.setText(state.getSkladnik().getNazwa());
            holder.name.setChecked(state.isChoosen());

            holder.name.setTag(state);

            return convertView;
        }

    }


    public void buildListViewDialog(final List<Skladnik> przepisy) {
        if (przepisy.size() == 0) {
            Toast.makeText(getApplicationContext(), "Nie znaleziono przepisow pasujacych do wzorca", Toast.LENGTH_LONG).show();
            return;
        }

        StrategySearch search = new StrategySearch(getApplicationContext(), przepisy);
        List<Przepis> wyszukane = search.search();
        ArrayList<String> tmp = new ArrayList<String>();
        for (Przepis p : wyszukane) {
            tmp.add(p.getNazwa());
        }
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                AdvancedSearch.this);
        builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Wyszukane Przepisy");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.select_dialog_singlechoice);
        for (String p : tmp) {
            arrayAdapter.add(p);
        }
        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        Intent intent = new Intent(AdvancedSearch.this, Przeepis.class);
                        intent.putExtra("KEY", strName);
                        startActivity(intent);
                    }
                });
        builderSingle.show();
    }


    private void checkButtonClick() {
        Button myButton = (Button) findViewById(R.id.button);

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<PrzepisSkladnikWybor> stateList = dataAdapter.stateList;
                List<Skladnik> wybrane = new ArrayList<Skladnik>();
                for (int i = 0; i < stateList.size(); i++) {
                    PrzepisSkladnikWybor state = stateList.get(i);
                    if (state.isChoosen()) {
                        wybrane.add(stateList.get(i).getSkladnik());

                    }
                }
                buildListViewDialog(wybrane);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        List<PrzepisSkladnikWybor> tmp = dataAdapter.stateList;
        SharedPreferences.Editor pref = this.getSharedPreferences("com.example.studenckieprzepisy", Context.MODE_PRIVATE).edit();
        for (PrzepisSkladnikWybor i: tmp){
            if (i.isChoosen()) {
                Log.d("PS ", i.getSkladnik().getNazwa());
                pref.putString(i.getSkladnik().getNazwa(),i.getSkladnik().getNazwa());
            }
        }
        pref.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences.Editor pref = AdvancedSearch.this.getSharedPreferences("com.example.studenckieprzepisy", Context.MODE_PRIVATE).edit();
        pref.clear().commit();
    }
}