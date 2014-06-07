package com.example.studenckieprzepisy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.studenckieprzepisy.Database.Bridge.DateBridge;
import com.example.studenckieprzepisy.Database.Bridge.SqlBridge;
import com.example.studenckieprzepisy.Database.DatabaseObjects.Przepis;
import com.example.studenckieprzepisy.Database.Factory.Database;
import com.example.studenckieprzepisy.RecipeView.Przeepis;

import java.io.IOException;
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
        DateBridge db = new SqlBridge(new Database(getActivity().getApplicationContext(), null, null, 1));
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

        private Bitmap getBitmap(String name) throws IOException {
            AssetManager asset = getActivity().getAssets();
            InputStream is = asset.open(name);
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = 40;
            return BitmapFactory.decodeStream(is, null, o2);
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
            Drawable d = null;
            try {
                if (lista.size() < position) {
                    Log.d("ZDJECIE", "" + przepis.get(position).getZdjecie());

                    if (przepis.get(position).getZdjecie().length() < 9) {
                        imageView.setImageBitmap(getBitmap(przepis.get(position).getZdjecie()));
                    } else {
                        try {
                            BitmapFactory.Options o2 = new BitmapFactory.Options();
                            o2.inSampleSize = 40;
                            Bitmap bitmap = BitmapFactory.decodeFile(przepis.get(position).getZdjecie(), o2);
                            imageView.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        if (d != null)
                            imageView.setImageDrawable(d);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } //catch out of memory
                    if (d != null)
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