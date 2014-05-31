package com.example.studenckieprzepisy;

import Database.Database;
import Database.Przepis;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 01.06.14.
 */
public class PrzepisFromKategoria extends Activity {
    private ListView list;
    MyCustomAdapter dataAdapter = null;
    private int idKategorii = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.przepis_from_kategoria);
        list = (ListView) findViewById(R.id.listView);
        Bundle extra = getIntent().getExtras();
        idKategorii = extra.getInt("idkategorii",0);
        displayListView();
    }

    private void displayListView() {

        // Array list of countries
        Database db = new Database(getApplicationContext(), null, null, 1);
        List<Przepis> stateList = db.getPrzepisByKategoriaId(idKategorii);
        Log.d("Lista size", "" + stateList.size());
        // create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this, R.layout.category_row, stateList);
        ListView listView = (ListView) findViewById(R.id.listView);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Toast.makeText(getApplicationContext(),
                        "Clicked on : " , Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private class MyCustomAdapter extends ArrayAdapter<Przepis> {

        private List<Przepis> stateList;

        public MyCustomAdapter(Context context, int textViewResourceId,

                               List<Przepis> stateList) {
            super(context, textViewResourceId, stateList);
            this.stateList = stateList;
        }

        private class ViewHolder {
            ImageView img;
            TextView name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {

                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = vi.inflate(R.layout.category_row, null);

                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.name = (TextView) convertView
                        .findViewById(R.id.txt);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Przepis state = stateList.get(position);
            holder.name.setText(state.getNazwa());
            if (state.getZdjecie() == null)
                return convertView;

            AssetManager assetManager = getAssets();
            try {
                Drawable d;
                if (state.getZdjecie().length() < 9){
                    InputStream ims = assetManager.open(state.getZdjecie());
                    d = Drawable.createFromStream(ims, null);
                } else {
                    d = Drawable.createFromPath(state.getZdjecie());
                }
                holder.img.setImageDrawable(d);
            }catch (Exception ex){
                ex.printStackTrace();
            }


            return convertView;
        }

    }


}