package com.example.studenckieprzepisy;

import Database.Database;
import Database.Przepis;
import Database.Skladnik;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by piotr on 29.05.14.
 */
public class AddSkladnik extends Activity implements OnItemClickListener {

    ListView sklad;
    Button add;
    ArrayList<PrzepisSkladnikWybor> s;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addskladniklist);
        s = new ArrayList<PrzepisSkladnikWybor>();
        Database db = new Database(getApplicationContext(), null, null, 1);
        List<Skladnik> skladniki = db.getSkladniki();
        for (Skladnik tmp : skladniki){
            s.add(new PrzepisSkladnikWybor(tmp));
        }
        sklad = (ListView) findViewById(R.id.listView);

        ListAdapter c= new CheckBoxList(this, s);
        sklad.setAdapter(c);
        sklad.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        sklad.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        CheckBox cb = (CheckBox) v.findViewById(R.id.wybor);
        TextView tv = (TextView) v.findViewById(R.id.nazwa);
        Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_LONG).show();
        if (cb.isChecked()) {
            Toast.makeText(getApplicationContext(), "A",Toast.LENGTH_LONG).show();
        } else if (!cb.isChecked()) {
            Toast.makeText(getApplicationContext(), "B", Toast.LENGTH_LONG).show();
        }
        s.get(position).setChoosen(cb.isChecked());
    }
}