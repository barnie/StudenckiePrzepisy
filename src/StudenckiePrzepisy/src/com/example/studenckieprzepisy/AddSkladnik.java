package com.example.studenckieprzepisy;

import Database.Database;
import Database.Przepis;
import Database.Skladnik;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by piotr on 29.05.14.
 */
public class AddSkladnik extends Activity {

    private Button b;
    private ListView list;
    MyCustomAdapter dataAdapter = null;
    private String nazwa;
    private String opis;
    private ArrayList<String> spinner_miary;
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
        //getting data
        spinner_miary = new ArrayList<String>();
        spinner_miary.add("ALA");
        spinner_miary.add("MA");
        spinner_miary.add("KOTA");
        for (int i = 0; i < liczbelki.length; liczbelki[i] = i, ++i);
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
                        PrzepisSkladnikWybor _state = (PrzepisSkladnikWybor) cb.getTag();
                        final Dialog alertbox = new Dialog(getContext());

                        LayoutInflater li = LayoutInflater.from(getContext());
                        final View promptsView = li.inflate(R.layout.two_spinners,null);
                        Spinner s = (Spinner) promptsView.findViewById(R.id.spinner);
                        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_item, spinner_miary);
                        s.setAdapter(adapter_state);
                        Spinner liczby = (Spinner) promptsView.findViewById(R.id.spinner2);
                        ArrayAdapter<Integer> adapter_liczby = new ArrayAdapter<Integer>(getContext(),
                                android.R.layout.simple_spinner_item, liczbelki);
                        liczby.setAdapter(adapter_liczby);
                        Button b = (Button) promptsView.findViewById(R.id.ok);
                        alertbox.setContentView(promptsView);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Spinner s = (Spinner) promptsView.findViewById(R.id.spinner);
                                Toast.makeText(getContext(),"BAZINGA" + s.getSelectedItem(),Toast.LENGTH_LONG).show();
                                alertbox.dismiss();

                            }
                        });

                        // show it
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

    private void checkButtonClick() {

        Button myButton = (Button) findViewById(R.id.button);

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("Selected Countries are...\n");

                ArrayList<PrzepisSkladnikWybor> stateList = dataAdapter.stateList;

                for (int i = 0; i < stateList.size(); i++) {
                    PrzepisSkladnikWybor state = stateList.get(i);

                    if (state.isChoosen()) {
                        responseText.append("\n" + state.getSkladnik().toString() + "@" + state.getIle() + " @" + state.getMiara());
                    }
                }

                Toast.makeText(getApplicationContext(), responseText,
                        Toast.LENGTH_LONG).show();
            }
        });
    }


}