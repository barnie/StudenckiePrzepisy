package com.example.studenckieprzepisy;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by piotr on 29.05.14.
 */
public class CheckBoxList extends BaseAdapter {

    List<PrzepisSkladnikWybor> lista;
    Activity context;

    public CheckBoxList(Activity context, List<PrzepisSkladnikWybor> lista) {
        super();
        this.lista = lista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }



    @Override
    public int getItemViewType(int position) {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.add_skladnik, null);
            holder = new ViewHolder();

            holder.apkName = (TextView) convertView
                    .findViewById(R.id.nazwa);
            holder.ck1 = (CheckBox) convertView
                    .findViewById(R.id.wybor);
            holder.ck1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    lista.get(position).setChoosen(isChecked);
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.apkName.setText(lista.get(position).getSkladnik().getNazwa());
        if (lista.get(position).choosen)
            holder.ck1.setChecked(true);
        else
            holder.ck1.setChecked(false);


        return convertView;
    }

    private class ViewHolder {
        TextView apkName;
        CheckBox ck1;
    }

}
