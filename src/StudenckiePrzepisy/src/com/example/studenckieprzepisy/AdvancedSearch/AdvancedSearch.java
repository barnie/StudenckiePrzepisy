package com.example.studenckieprzepisy.AdvancedSearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

        // Array list of countries
        ArrayList<PrzepisSkladnikWybor> stateList = new ArrayList<PrzepisSkladnikWybor>();
        DateBridge db = new SqlBridge(new Database(getApplicationContext(), null, null, 1));
        List<Skladnik> skladniczki = db.getSkladniki();
        for (Skladnik i : skladniczki)
            stateList.add(new PrzepisSkladnikWybor(i, false));

        // create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this, R.layout.addskladniklist, stateList);
        ListView listView = (ListView) findViewById(R.id.listView);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
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


    public void buildListViewDialog(List<String> przepisy) {
        if (przepisy.size() == 0) {
            Toast.makeText(getApplicationContext(), "Nie znaleziono przepisow pasujacych do wzorca", Toast.LENGTH_LONG).show();
            return;
        }
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                AdvancedSearch.this);
        builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Wyszukane Przepisy");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.select_dialog_singlechoice);
        for (String p : przepisy) {
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
                StrategySearch search = new StrategySearch(getApplicationContext(), wybrane);
                List<Przepis> wyszukane = search.search();
                ArrayList<String> tmp = new ArrayList<String>();
                for (Przepis p : wyszukane) {
                    tmp.add(p.getNazwa());
                }
                buildListViewDialog(tmp);
            }
        });
    }
}