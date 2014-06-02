package com.example.studenckieprzepisy.AddRecipe;

import com.example.studenckieprzepisy.Database.Database;
import com.example.studenckieprzepisy.Database.Skladnik;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.example.studenckieprzepisy.MainActivity;
import com.example.studenckieprzepisy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 29.05.14.
 */
public class AddSkladnik extends Activity {

    private Button b;
    private ListView list;
    MyCustomAdapter dataAdapter = null;
    private String nazwa;
    private String opis;
    private String zdjecie = "";
    private int idkategorii = 0;
    private List<String> spinner_miary;
    private Integer liczbelki[] = new Integer[2000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addskladniklist);
        b = (Button) findViewById(R.id.button);
        list = (ListView) findViewById(R.id.listView);
        //init layouts
        Bundle extra = getIntent().getExtras();
        nazwa = extra.getString("nazwa");
        opis = extra.getString("opis");
        zdjecie = extra.getString("zdjecie");
        idkategorii = extra.getInt("kategoria");
        //getting data
        Database db = new Database(getApplicationContext(), null, null, 1);
        spinner_miary = db.getMiara();
        for (int i = 0; i < liczbelki.length; liczbelki[i] = i, ++i) ;
        displayListView();
        checkButtonClick();


    }

    private void displayListView() {

        // Array list of countries
        ArrayList<PrzepisSkladnikWybor> stateList = new ArrayList<PrzepisSkladnikWybor>();
        Database db = new Database(getApplicationContext(), null, null, 1);
        List<Skladnik> skladniczki = db.getSkladniki();
        for (Skladnik i : skladniczki)
            stateList.add(new PrzepisSkladnikWybor(i, false));

        // create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this, R.layout.addskladniklist, stateList);
        ListView listView = (ListView) findViewById(R.id.listView);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

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
                        final Dialog alertbox = new Dialog(getContext());

                        LayoutInflater li = LayoutInflater.from(getContext());
                        final View promptsView = li.inflate(R.layout.two_spinners, null);
                        Spinner s = (Spinner) promptsView.findViewById(R.id.spinner);
                        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_item, spinner_miary);
                        s.setAdapter(adapter_state);
                        Spinner liczby = (Spinner) promptsView.findViewById(R.id.spinner2);
                        ArrayAdapter<Integer> adapter_liczby = new ArrayAdapter<Integer>(getContext(),
                                android.R.layout.simple_spinner_item, liczbelki);
                        liczby.setAdapter(adapter_liczby);
                        Button b = (Button) promptsView.findViewById(R.id.ok);
                        b.setText("OK");
                        alertbox.setContentView(promptsView);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Spinner s = (Spinner) promptsView.findViewById(R.id.spinner);
                                Spinner s1 = (Spinner) promptsView.findViewById(R.id.spinner2);
                                _state.setMiara(s.getSelectedItem().toString());
                                _state.setIle(s1.getSelectedItemId());
                                alertbox.dismiss();

                            }
                        });

                        // show it
                        if (cb.isChecked())
                            alertbox.show();
                        _state.setMiara("ZAZNACZONO");
                        _state.getSkladnik().setIle(32);
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


    public void clearState() {
        SharedPreferences.Editor pref = this.getSharedPreferences("com.example.studenckieprzepisy", Context.MODE_PRIVATE).edit();
        pref.remove("nazwa");
        pref.remove("opis");
        pref.remove("kategoria");
        pref.remove("zdjecie");
        pref.commit();
    }

    private void checkButtonClick() {
        Button myButton = (Button) findViewById(R.id.button);

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<PrzepisSkladnikWybor> stateList = dataAdapter.stateList;
                ArrayList<PrzepisSkladnikWybor> wybrane = new ArrayList<PrzepisSkladnikWybor>();

                clearState();
                for (int i = 0; i < stateList.size(); i++) {
                    PrzepisSkladnikWybor state = stateList.get(i);
                    if (state.isChoosen()) {
                        wybrane.add(stateList.get(i));
                    }
                }

                Database db = new Database(getApplicationContext(), null, null, 1);
                db.addPrzepis(idkategorii, nazwa, opis, zdjecie, wybrane);
                Intent intent = new Intent(AddSkladnik.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }

}