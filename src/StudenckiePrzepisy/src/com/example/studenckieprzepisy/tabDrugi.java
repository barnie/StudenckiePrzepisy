package com.example.studenckieprzepisy;

import Database.Database;
import Database.Przepis;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class tabDrugi extends ListFragment {
    ListView list;
    String[] web;
    Integer[] imageId;
    List<Przepis> przepis;
    CustomList adapter;

    HashMap<Integer, Drawable> lista = new HashMap<Integer, Drawable>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Database db = new Database(getActivity().getApplicationContext(), null, null, 1);
        przepis = db.getPrzepisy();
        web = new String[przepis.size()];
        imageId = new Integer[przepis.size()];
        for (int i = 0; i < przepis.size(); i++) {
            web[i] = przepis.get(i).getNazwa();
            imageId[i] = R.drawable.pingwinek;
        }
        adapter = new
                CustomList(getActivity(), web, imageId);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int arg2, long arg3) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                        getActivity());
                builderSingle.setTitle("Usuwanie Przepisu").setMessage("Na pewno chcesz usunac przepis?");
                builderSingle.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Database db = new Database(getActivity().getApplicationContext(), null, null, 1);
                        db.removePrzepis(przepis.get(arg2));
                        adapter.notifyDataSetChanged();
                        getActivity().finish();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                builderSingle.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builderSingle.create();
                builderSingle.show();

                return true;
            }
        });
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
                    try {
                        imageView.setImageDrawable(d);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } //catch out of memory
                    lista.put(position, d);
                } else {
                    try {
                        imageView.setImageDrawable(lista.get(position));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return rowView;
        }


    }
}