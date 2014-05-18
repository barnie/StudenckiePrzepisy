package com.example.studenckieprzepisy;

import Database.Database;
import Database.Kategoria;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import Database.DatabaseInit;
import java.util.List;

public class TabPierwszy extends ListFragment {
    ListView list;
    List<Kategoria> category;
    String[] web;
    Integer[] imageId;

    public Integer getDrawableId(String name) {
        if (getActivity().getResources().getIdentifier(name, "drawable", getActivity().getPackageName()) != 0)
            return this.getResources().getIdentifier(name, "drawable", getActivity().getPackageName());
        else
            return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Database db = new Database(getActivity().getApplicationContext(), null, null, 1);
        DatabaseInit a = new DatabaseInit(getActivity().getApplicationContext());
        category = db.getKategorie();
        for (int i = 0; i < category.size(); i++)
            System.out.println(category.get(i).toString());
        web = new String[category.size()];
        imageId = new Integer[category.size()];
        for (int i = 0; i < category.size(); i++) {
            web[i] = category.get(i).getName();
            imageId[i] = 0;
        }
        for (int i = 0; i < category.size(); i++) {
            imageId[i] = getDrawableId(category.get(i).getImage());
        }
        for (int i = 0; i < category.size(); i++) {
            if (imageId[i] < 0)
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
        Toast.makeText(getActivity().getApplicationContext(), ":D", Toast.LENGTH_LONG).show();
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
            return rowView;
        }


    }
}
