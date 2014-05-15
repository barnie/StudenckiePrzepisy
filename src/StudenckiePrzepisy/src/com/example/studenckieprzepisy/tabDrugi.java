package com.example.studenckieprzepisy;

import Database.Database;
import Database.Przepis;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

public class tabDrugi extends ListFragment {
    ListView list;
    String[] web;
    Integer[] imageId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Database db = new Database(getActivity().getApplicationContext(),null,null,1);
        List<Przepis> przepis = db.getPrzepisy();
        for (Przepis p : przepis){
            Log.d("PRZEPISY!", p.toString());
        }
        web = new String[przepis.size()];
        imageId = new Integer[przepis.size()];
        for (int i = 0; i < przepis.size(); i++){
            web[i] = przepis.get(i).getNazwa();
            imageId[i] = R.drawable.ic_launcher;
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