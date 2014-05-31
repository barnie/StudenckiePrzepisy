package com.example.studenckieprzepisy;

import Database.Database;
import Database.Przepis;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class tabDrugi extends ListFragment {
    ListView list;
    String[] web;
    Integer[] imageId;
    List<Przepis> przepis;


    HashMap<Integer, Drawable> lista = new HashMap<Integer, Drawable>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Database db = new Database(getActivity().getApplicationContext(), null, null, 1);
        przepis = db.getPrzepisy();
        for (Przepis p : przepis) {
            if (p.getZdjecie() != null)
                Log.d("PRZEPISY!", p.toString());
        }
        web = new String[przepis.size()];
        imageId = new Integer[przepis.size()];
        for (int i = 0; i < przepis.size(); i++) {
            web[i] = przepis.get(i).getNazwa();
            imageId[i] = R.drawable.pingwinek;
        }
        CustomList adapter = new
                CustomList(getActivity(), web, imageId);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), Przeepis.class);
        intent.putExtra("KEY", przepis.get(position).getNazwa());
        startActivity(intent);
    }

    class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        private final String[] web;
        private final Integer[] imageId;

        public CustomList(Activity context,
                          String[] web, Integer[] imageId) {
            super(context, R.layout.category_row, web);
            this.context = context;
            this.web = web;
            this.imageId = imageId;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.category_row, null, true);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
            txtTitle.setText(web[position]);
            imageView.setImageResource(imageId[position]);
            AssetManager assetManager = getActivity().getAssets();
            if (przepis.get(position).getZdjecie() == null)
                return rowView;
            try {
                if (lista.size() < position) {
                    Log.d("ZDJECIE", "" + przepis.get(position).getZdjecie());
                    Drawable d;
                    if (przepis.get(position).getZdjecie().length() < 9) {
                        InputStream ims = assetManager.open(przepis.get(position).getZdjecie());
                        d = Drawable.createFromStream(ims, null);
                    } else {
                        d = Drawable.createFromPath(przepis.get(position).getZdjecie());
                    }
                    imageView.setImageDrawable(d);
                    lista.put(position, d);
                    imageView.setImageDrawable(d);
                } else {
                    imageView.setImageDrawable(lista.get(position));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return rowView;
        }


    }
}